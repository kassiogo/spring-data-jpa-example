package com.kassiogo.siacon.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EnumSet;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

import jakarta.persistence.EntityManagerFactory;

@SpringBootApplication
public class DDLGenerator {

  @Value("${app.ddl-target-type}")
  String ddlTargetType;



  @Autowired
  private EntityManagerFactory entityManagerFactory;

  private Class<?> getClass(String className, String packageName) {
    try {
      return Class.forName(packageName + "."
          + className.substring(0, className.lastIndexOf('.')));
    } catch (ClassNotFoundException e) {
      return null;
    }
  }

  private String outputFileName = "ddl.sql";

  private void genDDL() {
    var packageName = "com.kassiogo.siacon.model";
    var metadataSources = new MetadataSources(
        new StandardServiceRegistryBuilder()
            .applySettings(entityManagerFactory.getProperties())
            .build());

    InputStream stream = ClassLoader.getSystemClassLoader()
        .getResourceAsStream(packageName.replaceAll("[.]", "/"));

    var reader = new BufferedReader(new InputStreamReader(stream));

    reader
        .lines()
        .filter(line -> line.endsWith(".class"))
        .map(line -> getClass(line, packageName))
        .forEach(c -> {
          System.out.println("adicionando classe " + c.getName());
          metadataSources.addAnnotatedClass(c);
        });
    var metadata = metadataSources.buildMetadata();

    var schemaExport = new SchemaExport();
    schemaExport.setFormat(true);
    schemaExport.setOutputFile(outputFileName);
    schemaExport.create(EnumSet.of(TargetType.valueOf(ddlTargetType)), metadata);
    System.out.println("acho que foi..." + outputFileName);
  }

  public static void main(String[] args) {
    ConfigurableEnvironment environment = new StandardEnvironment();
    environment.setActiveProfiles();

    SpringApplication sa = new SpringApplication(DDLGenerator.class);
    sa.setEnvironment(environment);
    sa.setAdditionalProfiles("pgdb","dev");
    sa.run(args);
  }

  @Bean
  CommandLineRunner commandLineRunner(ConfigurableApplicationContext ctx) {
    return args -> {
      this.genDDL();
      ctx.close();
    };
  }
}
