package fr.limayrac.pfeback.db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class V1_0_2__AjoutUsers extends BaseJavaMigration implements SpringJDBCTemplateProvider{

    @Override
    public void migrate(Context context) throws Exception {
        JdbcTemplate jdbcTemplate = jdbcTemplate(context);
        createTable(jdbcTemplate);

        // Charger les photos
        byte[] photoOrtho = loadImage("image/jpg/ortho.png");
        byte[] photoPatient = loadImage("image/jpg/patient.png");
        byte[] photoPatientPublic = loadImage("image/jpg/public.jpg");

        //Orthophoniste
        insertOrthophoniste(
                jdbcTemplate,
                "ortho@limayrac.fr", // login
                new BCryptPasswordEncoder().encode("ortho"), // password
                "0605060708", // téléphone
                "Martin", // nom
                "Claire", // prénom
                "5 rue des Écoles", // adresse
                "12345678900012", // SIRET
                "987654321", // RPPS
                photoOrtho // photo
        );
        Long orthoId = jdbcTemplate.queryForObject("SELECT user_id FROM orthophoniste join users on id = user_id WHERE login = 'ortho@limayrac.fr'", Long.class);


        //Patient
        insertPatient(
                jdbcTemplate,
                "patient@limayrac.fr", // login
                new BCryptPasswordEncoder().encode("patient"), // password
                "0601020304", // téléphone
                "Durand", // nom
                "Léo", // prénom
                "Durand", // nomParent
                "Julie", // prénomParent
                "42 rue des Lilas", // adresse
                photoPatient, // photo
                orthoId // orthophonisteId
        );

        //Patient public
        insertPatient(
                jdbcTemplate,
                "patient2@limayrac.fr",                     // login
                new BCryptPasswordEncoder().encode("patient"), // password
                "0789456123",                                 // téléphone
                "Martin",                                     // nom
                "Léo",                                        // prénom
                "Martin",                                     // nomParent
                "Claire",                                     // prénomParent
                "15 avenue Victor Hugo",                      // adresse
                photoPatientPublic,                                         // photo
                null                                          // orthophonisteId
        );

        //Administrateur
        insertAdministrateur(
                jdbcTemplate,
                "admin@limayrac.fr", // login
                new BCryptPasswordEncoder().encode("admin"), // password
                "0611223344", // téléphone
                "Morel", // nom
                "Camille" // prénom
        );

    }

    public byte[] loadImage(String fileName) {
        InputStream stream = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName), "Fichier non trouvé : " + fileName);
        try {
            byte[] data = stream.readAllBytes();
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(final JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("CREATE TABLE users(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "login VARCHAR(255)," +
                "password VARCHAR(255)," +
                "role tinyint(1) NOT NULL," +
                "telephone VARCHAR(255), " +
                "actif bit(1) NOT NULL," +
                "user_type VARCHAR(50) NOT NULL" +
                ")");

        // Orthophoniste
        jdbcTemplate.execute("CREATE TABLE orthophoniste(" +
                "adresse VARCHAR(255)," +
                "nom VARCHAR(255)," +
                "prenom VARCHAR(255)," +
                "siret VARCHAR(255)," +
                "rpps VARCHAR(255)," +
                "photo_orthophoniste LONGBLOB," +
                "user_id BIGINT NOT NULL," +
                "CONSTRAINT `fk_orthophoniste__user` FOREIGN KEY (user_id) REFERENCES users(id)" +
                ")");

        // Patient
        jdbcTemplate.execute("CREATE TABLE patient(" +
                "adresse VARCHAR(255)," +
                "nom VARCHAR(255)," +
                "prenom VARCHAR(255)," +
                "nom_parent VARCHAR(255)," +
                "prenom_parent VARCHAR(255)," +
                "orthophoniste BIGINT," +
                "CONSTRAINT `fk_orthophoniste`FOREIGN KEY (orthophoniste) REFERENCES orthophoniste(user_id)," +
                "photo_patient LONGBLOB," +
                "abonnement BIGINT," +
                "user_id BIGINT NOT NULL," +
                "CONSTRAINT `fk_patient_user` FOREIGN KEY (user_id) REFERENCES users(id)" +
                ")");

        jdbcTemplate.execute("CREATE TABLE administrateur(" +
                "nom VARCHAR(255)," +
                "prenom VARCHAR(255)," +
                "user_id BIGINT NOT NULL," +
                "CONSTRAINT `fk_administrateur_user` FOREIGN KEY (user_id) REFERENCES users(id)" +
                ")");
    }

    private Long insertUser(JdbcTemplate jdbcTemplate, String login, String password, int role, String telephone, String userType) {
        jdbcTemplate.update("INSERT INTO users (login, password, role, telephone, actif, user_type) VALUES (?, ?, ?, ?, ?, ?)",
                login, password, role, telephone, true, userType);

        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    public void insertPatient(JdbcTemplate jdbcTemplate,
                              String login, String password, String telephone,
                              String nom, String prenom, String nomParent, String prenomParent,
                              String adresse, byte[] photo, Long orthophonisteId) {

        Long userId = insertUser(jdbcTemplate, login, password, 2, telephone, "PATIENT"); // 1 = rôle patient

        jdbcTemplate.update("INSERT INTO patient (adresse, nom, prenom, nom_parent, prenom_parent, orthophoniste, photo_patient, user_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                adresse, nom, prenom, nomParent, prenomParent, orthophonisteId, photo, userId);
    }

    public void insertOrthophoniste(JdbcTemplate jdbcTemplate,
                                    String login, String password, String telephone,
                                    String nom, String prenom, String adresse,
                                    String siret, String rpps, byte[] photo) {

        Long userId = insertUser(jdbcTemplate, login, password, 1, telephone, "ORTHOPHONISTE"); // 2 = rôle orthophoniste

        jdbcTemplate.update("INSERT INTO orthophoniste (adresse, nom, prenom, siret, rpps, photo_orthophoniste, user_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                adresse, nom, prenom, siret, rpps, photo, userId);
    }

    public void insertAdministrateur(JdbcTemplate jdbcTemplate,
                                     String login, String password, String telephone,
                                     String nom, String prenom) {

        Long userId = insertUser(jdbcTemplate, login, password, 0, telephone, "ADMINISTRATEUR"); // 0 = rôle admin

        jdbcTemplate.update("INSERT INTO administrateur (nom, prenom, user_id) VALUES (?, ?, ?)",
                nom, prenom, userId);
    }

}
