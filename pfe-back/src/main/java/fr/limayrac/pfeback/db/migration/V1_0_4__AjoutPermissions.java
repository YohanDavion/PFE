package fr.limayrac.pfeback.db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;

public class V1_0_4__AjoutPermissions extends BaseJavaMigration implements SpringJDBCTemplateProvider {

    @Override
    public void migrate(Context context) throws Exception {
        JdbcTemplate jdbcTemplate = jdbcTemplate(context);
        createTables(jdbcTemplate);
        insertPermissions(jdbcTemplate);
        insertRolePermissions(jdbcTemplate);
    }

    public void createTables(final JdbcTemplate jdbcTemplate) {
        // Table des permissions
        jdbcTemplate.execute("CREATE TABLE permission(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(255) UNIQUE NOT NULL," +
                "description VARCHAR(500)," +
                "type VARCHAR(50) NOT NULL" +
                ")");

        // Table de liaison rôles-permissions
        jdbcTemplate.execute("CREATE TABLE role_permissions(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "role VARCHAR(50) NOT NULL," +
                "permission_id BIGINT NOT NULL," +
                "CONSTRAINT fk_role_permission FOREIGN KEY (permission_id) REFERENCES permission(id)" +
                ")");
    }

    public void insertPermissions(final JdbcTemplate jdbcTemplate) {
        // Permissions pour les animations
        jdbcTemplate.execute("INSERT INTO permission (name, description, type) VALUES " +
                "('animation.read', 'Voir les animations', 'read'), " +
                "('animation.create', 'Créer des animations', 'write'), " +
                "('animation.update', 'Modifier des animations', 'write'), " +
                "('animation.delete', 'Supprimer des animations', 'delete'), " +
                "('animation.activate', 'Activer/Désactiver des animations', 'write')");

        // Permissions pour les séries
        jdbcTemplate.execute("INSERT INTO permission (name, description, type) VALUES " +
                "('serie.read', 'Voir les séries', 'read'), " +
                "('serie.create', 'Créer des séries', 'write'), " +
                "('serie.update', 'Modifier des séries', 'write'), " +
                "('serie.delete', 'Supprimer des séries', 'delete'), " +
                "('serie.activate', 'Activer/Désactiver des séries', 'write')");

        // Permissions pour les patients
        jdbcTemplate.execute("INSERT INTO permission (name, description, type) VALUES " +
                "('patient.read', 'Voir les patients', 'read'), " +
                "('patient.create', 'Créer des patients', 'write'), " +
                "('patient.update', 'Modifier des patients', 'write'), " +
                "('patient.delete', 'Supprimer des patients', 'delete'), " +
                "('patient.manage_access', 'Gérer les droits d''accès des patients', 'admin')");

        // Permissions pour les orthophonistes
        jdbcTemplate.execute("INSERT INTO permission (name, description, type) VALUES " +
                "('orthophoniste.read', 'Voir les orthophonistes', 'read'), " +
                "('orthophoniste.create', 'Créer des orthophonistes', 'write'), " +
                "('orthophoniste.update', 'Modifier des orthophonistes', 'write'), " +
                "('orthophoniste.delete', 'Supprimer des orthophonistes', 'delete')");

        // Permissions pour les abonnements
        jdbcTemplate.execute("INSERT INTO permission (name, description, type) VALUES " +
                "('abonnement.read', 'Voir les abonnements', 'read'), " +
                "('abonnement.subscribe', 'S''abonner', 'write'), " +
                "('abonnement.manage', 'Gérer les abonnements', 'admin')");

        // Permissions système
        jdbcTemplate.execute("INSERT INTO permission (name, description, type) VALUES " +
                "('system.admin', 'Administration système', 'admin'), " +
                "('role.manage', 'Gérer les rôles et permissions', 'admin'), " +
                "('statistics.view', 'Voir les statistiques', 'read')");
    }

    public void insertRolePermissions(final JdbcTemplate jdbcTemplate) {
        // Permissions pour ADMINISTRATEUR (accès complet)
        jdbcTemplate.execute("INSERT INTO role_permissions (role, permission_id) " +
                "SELECT 'ADMINISTRATEUR', id FROM permission");

        // Permissions pour ORTHOPHONISTE
        jdbcTemplate.execute("INSERT INTO role_permissions (role, permission_id) " +
                "SELECT 'ORTHOPHONISTE', id FROM permission WHERE name IN (" +
                "'animation.read', 'animation.create', 'animation.update', 'animation.activate', " +
                "'serie.read', 'serie.create', 'serie.update', 'serie.activate', " +
                "'patient.read', 'patient.create', 'patient.update', 'patient.manage_access', " +
                "'statistics.view'" +
                ")");

        // Permissions pour PATIENT
        jdbcTemplate.execute("INSERT INTO role_permissions (role, permission_id) " +
                "SELECT 'PATIENT', id FROM permission WHERE name IN (" +
                "'animation.read', 'serie.read', 'abonnement.read', 'abonnement.subscribe'" +
                ")");
    }
}