package fr.limayrac.pfeback.db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;

public class V1_0_3__AjoutAbonnements extends BaseJavaMigration implements SpringJDBCTemplateProvider{

    @Override
    public void migrate(Context context) throws Exception {
        JdbcTemplate jdbcTemplate = jdbcTemplate(context);
        createTable(jdbcTemplate);
        insertTable(jdbcTemplate);
    }

    public void createTable(final JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("CREATE TABLE abonnement(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "libelle VARCHAR(255)," +
                "description VARCHAR(255)," +
                "montant DOUBLE" +
                ")");
    }

    public void insertTable(final JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("INSERT INTO abonnement (libelle, description, montant) VALUES " +
                "('Classique', 'Un abonnement pour vous entraîner à la prononciation', 10), "
                + "('Groupe', 'Un abonnement pour vous entraîner à la prononciation vous et tous les membres de votre famille', 25)"
        );
    }
}
