import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.JointClassification;
import com.aliasi.classify.JointClassifier;
import com.aliasi.lm.NGramProcessLM;
import com.aliasi.util.AbstractExternalizable;
import java.io.File;
import java.io.IOException;
import com.aliasi.util.Files;

public class ClassifyPoems {

    private static File _trainDirectory = new File("src/main/resources/");
    private static File _testDirectory = new File("src/main/resources/TestPoems");
    private static String[] _categories= {"DarkPoems", "LovePoems", "SadPoems"};

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        int phraseLength = 10;
        DynamicLMClassifier<NGramProcessLM> classifier= DynamicLMClassifier.createNGramProcess(_categories, phraseLength);

        for (String categoryName: _categories) {
            File classifyDirectory = new File(_trainDirectory,categoryName);
            String[] trainingFiles = classifyDirectory.list();
            if (trainingFiles != null){
                for (String fileName:trainingFiles) {
                    File poem = new File(classifyDirectory, fileName);
                    String text = Files.readFromFile(poem, "ISO-8859-1");
                    System.out.println("Training " + categoryName + "/" + fileName);
                    Classification classification = new Classification(categoryName);
                    Classified<CharSequence> classified = new Classified<>(text, classification);
                    classifier.handle(classified);
                }
            }
            else {
                System.out.print("Training Files are Empty");
                System.exit(1);
            }
        }

        @SuppressWarnings("unchecked")
        JointClassifier<CharSequence> compiledClassifier
            = (JointClassifier<CharSequence>)AbstractExternalizable.compile(classifier);

        String[] testingFiles = _testDirectory.list();

        if (testingFiles != null){
            for (String poemName: testingFiles) {
                String text = Files.readFromFile(new File(_testDirectory, poemName), "ISO-8859-1");
                System.out.print("Testing " + poemName + " ");

                JointClassification jc = compiledClassifier.classify(text);
                String bestCategory = jc.bestCategory();
                String details = jc.toString();
                System.out.println("Got best category of: " + bestCategory);
                System.out.print(details);
                System.out.println("---------------");
            }
        }
        else {
            System.out.print("Testing Files are Empty");
            System.exit(1);
        }
    }
}
