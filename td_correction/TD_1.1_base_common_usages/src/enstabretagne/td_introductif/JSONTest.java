/*
 *
 */
package enstabretagne.td_introductif;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


// TODO: Auto-generated Javadoc

/**
 * The Class JSONTest.
 */
public class JSONTest {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        Dog d1 = new Dog();
        d1.name = "Naboo";
        d1.age = 1;

        Dog d2 = new Dog();
        d2.name = "Tatooine";
        d2.age = 13;

        Dog d3 = new Dog();
        d3.name = "Coruscant";
        d3.age = 4;

        Dog d4 = new Dog();
        d4.name = "Hoth";
        d4.age = 2;
        d3.children.add(d4);

        d2.children.add(d1);
        d2.children.add(d3);

        JsonbConfig config = new JsonbConfig().withFormatting(true);
        Jsonb jsonb = JsonbBuilder.create(config);
        String result = jsonb.toJson(d2);

        System.out.println(result);

        String targetDir = System.getProperty("user.dir") + "\\maconf";
        File d = new File(targetDir);
        d.mkdirs();

        targetDir = targetDir + "\\test.txt";
        System.out.println(targetDir);
        try {
            Files.write(Paths.get(targetDir), result.getBytes());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String content;
        Dog mond;
        try {
            content = new String(Files.readAllBytes(Paths.get(targetDir)));
            mond = jsonb.fromJson(content, Dog.class);
            System.out.println(mond);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
