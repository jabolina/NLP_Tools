# NLP Tools

Some tools to use in NLP. Phonetics, stopwords and Internet treatment to PT-BR.

<br>
<h2> Dependencies </h2>

Besides Java 8, Maven is needed. The version of Maven declared use the version 3.7.0.

<br>
<h2> Usage </h2>

After cloning the repo:

    $ mvn clean install

Then, go to your project and add this in pom.xml, inside the tag <dependencies>:

    <dependency>
        <groupId>com.nlp</groupId>
        <artifactId>nlptools</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
  
Update the changes, then to use, inside your Java code, where you want to treat an portuguese String, do:

    normalize("Coloque sua string aqui");

And the method returns the treated string.
