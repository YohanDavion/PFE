package fr.limayrac.pfeback.db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;

public class V1_0_1__AjoutAnimations extends BaseJavaMigration implements SpringJDBCTemplateProvider{

    @Override
    public void migrate(Context context) throws Exception {
        JdbcTemplate jdbcTemplate = jdbcTemplate(context);
        creationTable(jdbcTemplate);
        insertData(jdbcTemplate);
    }

    public void creationTable(final JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("create table media(" +
                "id BIGINT NOT NULL primary key auto_increment," +
                "mimetype varchar(255)," +
                "data LONGBLOB NOT NULL)");

        jdbcTemplate.execute("CREATE TABLE animation (" +
                "id BIGINT NOT NULL PRIMARY KEY auto_increment, " +
                "active BOOLEAN NOT NULL," +
                "gif BIGINT not null," +
                "image BIGINT NOT NULL, " +
                "son BIGINT NOT NULL, " +
                "FOREIGN KEY (gif) references media(id)," +
                "FOREIGN KEY (image) references media(id)," +
                "FOREIGN KEY (son) references media(id)" +
                ")");

    }

    public void insertData(final JdbcTemplate jdbcTemplate) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        InputStream stream = loader.getResourceAsStream("image/gif/chien.gif");
        //Chien
        try {
            jdbcTemplate.execute("insert into media (id, mimetype,data) values (1, 'image/gif', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        try {
            stream = loader.getResourceAsStream("image/jpg/chien.jpg");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (2, 'image/jpg', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        try {
            stream = loader.getResourceAsStream("son/chien.mp3");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (3, 'audio/mpeg', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        jdbcTemplate.execute("insert into animation (id, active, gif, image, son) values (1, true, 1, 2, 3)");

        //Chat
        try {
            stream = loader.getResourceAsStream("image/gif/chat.gif");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (4, 'image/gif', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        try {
            stream = loader.getResourceAsStream("image/jpg/chat.jpg");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (5, 'image/jpg', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        try {
            stream = loader.getResourceAsStream("son/chat.mp3");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (6, 'audio/mpeg', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        jdbcTemplate.execute("insert into animation (id, active, gif, image, son) values (2, true, 4, 5, 6)");

        //Hamster
        try {
            stream = loader.getResourceAsStream("image/gif/hamster.gif");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (7,'image/gif', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        try {
            stream = loader.getResourceAsStream("image/jpg/hamster.jpg");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (8,'image/jpg', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        try {
            stream = loader.getResourceAsStream("son/hamster.mp3");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (9,'audio/mpeg', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        jdbcTemplate.execute("insert into animation (id, active, gif, image, son) values (3, true, 7, 8, 9)");

        // Lapin
        try {
            stream = loader.getResourceAsStream("image/gif/lapin.gif");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (10,'image/gif', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        try {
            stream = loader.getResourceAsStream("image/jpg/lapin.jpg");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (11,'image/jpg', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        try {
            stream = loader.getResourceAsStream("son/lapin.mp3");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (12,'audio/mpeg', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        jdbcTemplate.execute("insert into animation (id, active, gif, image, son) values (4, true, 10, 11, 12)");

        // Poisson
        try {
            stream = loader.getResourceAsStream("image/gif/poisson.gif");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (13,'image/gif', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        try {
            stream = loader.getResourceAsStream("image/jpg/poisson.jpg");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (14,'image/jpg', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        try {
            stream = loader.getResourceAsStream("son/poisson.mp3");
            jdbcTemplate.execute("insert into media (id,mimetype,data) values (15,'audio/mpeg', '" + stream.readAllBytes() + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        jdbcTemplate.execute("insert into animation (id, active, gif, image, son) values (5, true, 13, 14, 15)");
    }


}
