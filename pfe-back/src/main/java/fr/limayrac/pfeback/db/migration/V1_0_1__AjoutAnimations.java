package fr.limayrac.pfeback.db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.Objects;

public class V1_0_1__AjoutAnimations extends BaseJavaMigration implements SpringJDBCTemplateProvider{

    @Override
    public void migrate(Context context) throws Exception {
        JdbcTemplate jdbcTemplate = jdbcTemplate(context);
        creationTable(jdbcTemplate);
        insertAnimation(jdbcTemplate);
        insertSeries(jdbcTemplate);
        insertRelations(jdbcTemplate);
    }

    public void creationTable(final JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("create table media(" +
                "id BIGINT NOT NULL primary key auto_increment," +
                "mimetype varchar(255)," +
                "data LONGBLOB NOT NULL)");

        jdbcTemplate.execute("CREATE TABLE animation (" +
                "id BIGINT NOT NULL PRIMARY KEY auto_increment, " +
                "libelle VARCHAR(255) not null," +
                "active BOOLEAN NOT NULL," +
                "gif BIGINT not null," +
                "image BIGINT NOT NULL, " +
                "son BIGINT NOT NULL, " +
                "FOREIGN KEY (gif) references media(id)," +
                "FOREIGN KEY (image) references media(id)," +
                "FOREIGN KEY (son) references media(id)" +
                ")");

        jdbcTemplate.execute("CREATE TABLE serie(" +
                "id BIGINT NOT NULL primary key auto_increment," +
                "active BOOLEAN NOT NULL," +
                "libelle VARCHAR(255) NOT NULL" +
                ")");

        jdbcTemplate.execute("CREATE TABLE serie_animations(" +
                "series_id BIGINT NOT NULL, " +
                "animations_id BIGINT NOT NULL, " +
                "FOREIGN KEY (series_id) references serie(id)," +
                "FOREIGN KEY (animations_id) references animation(id))");
    }

    public void insertMedia(final JdbcTemplate jdbcTemplate, Long id, final String mimetype, String resourcePath) throws IOException {
        InputStream stream = null;
        try {
            stream = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(resourcePath), "Fichier non trouvé : " + resourcePath);
            byte[] data = stream.readAllBytes();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO media (id, mimetype, data) VALUES (?, ?, ?)");
                ps.setLong(1, id);
                ps.setString(2, mimetype);
                ps.setBytes(3, data);
                return ps;
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (stream != null) stream.close();
        }
    }

    public void insertAnimation(final JdbcTemplate jdbcTemplate) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        // Chien
        insertMedia(jdbcTemplate, 1L, "image/gif", "image/gif/chien.gif");
        insertMedia(jdbcTemplate, 2L, "image/jpg", "image/jpg/chien.jpg");
        insertMedia(jdbcTemplate, 3L, "audio/mpeg", "son/chien.mp3");
        jdbcTemplate.execute("insert into animation (id, libelle, active, gif, image, son) values (1, 'Chien', true, 1, 2, 3)");
        // Chat
        insertMedia(jdbcTemplate, 4L, "image/gif", "image/gif/chat.gif");
        insertMedia(jdbcTemplate, 5L, "image/jpg", "image/jpg/chat.jpg");
        insertMedia(jdbcTemplate, 6L, "audio/mpeg", "son/chat.mp3");
        jdbcTemplate.execute("insert into animation (id, libelle, active, gif, image, son) values (2, 'Chat', true, 4, 5, 6)");
        // Hamster
        insertMedia(jdbcTemplate, 7L, "image/gif", "image/gif/hamster.gif");
        insertMedia(jdbcTemplate, 8L, "image/jpg", "image/jpg/hamster.jpg");
        insertMedia(jdbcTemplate, 9L, "audio/mpeg", "son/hamster.mp3");
        jdbcTemplate.execute("insert into animation (id, libelle, active, gif, image, son) values (3, 'Hamster', true, 7, 8, 9)");
        // Lapin
        insertMedia(jdbcTemplate, 10L, "image/gif", "image/gif/lapin.gif");
        insertMedia(jdbcTemplate, 11L, "image/jpg", "image/jpg/lapin.jpg");
        insertMedia(jdbcTemplate, 12L, "audio/mpeg", "son/lapin.mp3");
        jdbcTemplate.execute("insert into animation (id, libelle, active, gif, image, son) values (4, 'Lapin', true, 10, 11, 12)");
        // Poisson
        insertMedia(jdbcTemplate, 13L, "image/gif", "image/gif/poisson.gif");
        insertMedia(jdbcTemplate, 14L, "image/jpg", "image/jpg/poisson.jpg");
        insertMedia(jdbcTemplate, 15L, "audio/mpeg", "son/poisson.mp3");
        jdbcTemplate.execute("insert into animation (id, libelle, active, gif, image, son) values (5, 'Poisson', true, 13, 14, 15)");
    }

    public void insertSeries(final JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("INSERT INTO serie (id, active, libelle) VALUES (1, true, 'Animaux domestiques'), " +
                "(2, true, 'Animaux de la ferme'), " +
                "(3, true, 'Animaux de la jungle')");
    }

    public void insertRelations(final JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("INSERT INTO serie_animations(series_id, animations_id) VALUES " +
                "(1, 1), (1,2), (1,3), (1,4), (1,5), " +
                "(2, 4)");
    }
}
