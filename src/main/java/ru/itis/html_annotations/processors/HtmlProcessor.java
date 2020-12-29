package ru.itis.html_annotations.processors;

import com.google.auto.service.AutoService;
import ru.itis.html_annotations.annotations.HtmlForm;
import ru.itis.html_annotations.annotations.HtmlInput;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {
        "ru.itis.html_annotations.annotations.HtmlForm",
        "ru.itis.html_annotations.annotations.HtmlInput"
})
public class HtmlProcessor extends AbstractProcessor {

    private final String submit = "<input type=\"submit\" value=\"Send\"/>";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> htmlFormAnnotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element formAnnotatedElement : htmlFormAnnotatedElements) {
            System.out.println(formAnnotatedElement);
            String path = "/html/" + formAnnotatedElement.getSimpleName().toString().toLowerCase() + ".html";
            Path out = Paths.get(path);
            System.out.println(out);
            Set<? extends Element> htmlInputAnnotatedElements = roundEnv.getElementsAnnotatedWith(HtmlInput.class);

            try {
                BufferedWriter w = new BufferedWriter(new FileWriter("\\html\\file.txt"));
                w.write("adad");
                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                HtmlForm htmlForm = formAnnotatedElement.getAnnotation(HtmlForm.class);
                writer.write("<form action='" + htmlForm.action() +
                        " method='" + htmlForm.method() + "'/>" + '\n');
                for (Element inputAnnotatedElement : htmlInputAnnotatedElements) {
                    System.out.println(inputAnnotatedElement);
                    HtmlInput htmlInput = inputAnnotatedElement.getAnnotation(HtmlInput.class);
                    writer.write('\t' + "<input type=\"" + htmlInput.type() +
                            "\" name=\"" + htmlInput.name() +
                            "\" placeholder=\"" + htmlInput.placeholder() +
                            "\"/>" + '\n');
                }
                writer.write('\t' + submit + '\n');
                writer.write("</form>");
                System.out.println(1);
                writer.close();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return true;
    }
}
