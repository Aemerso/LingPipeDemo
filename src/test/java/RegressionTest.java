import com.aliasi.classify.*;
import com.aliasi.lm.NGramProcessLM;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Files;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anthony Emerson on 5/16/2017.
 */
public class RegressionTest {

    private static File _regressionDirectory = new File("src/main/resources/");
    private static String[] _categories= {"DarkPoems", "LovePoems", "SadPoems"};

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        int phraseLength = 10;
        DynamicLMClassifier<NGramProcessLM> classifier= DynamicLMClassifier.createNGramProcess(_categories, phraseLength);

        for (String categoryName: _categories) {
            File classifyDirectory = new File(_regressionDirectory,categoryName);
            String[] trainingFiles = classifyDirectory.list();
            if (trainingFiles != null){
                int trainCap = (int)(trainingFiles.length*0.8);
                for (int i = 0; i < trainCap; i++) {
                    File poem = new File(classifyDirectory, trainingFiles[i]);
                    String text = Files.readFromFile(poem, "ISO-8859-1");
                    System.out.println("Training " + categoryName + "/" + trainingFiles[i]);
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
                = (JointClassifier<CharSequence>) AbstractExternalizable.compile(classifier);

        JointClassifierEvaluator<CharSequence> evaluator = new JointClassifierEvaluator<>
                (compiledClassifier,_categories, true);

        for (String categoryName: _categories) {
            File classifyDirectory = new File(_regressionDirectory,categoryName);
            String[] testingFiles = classifyDirectory.list();
            if (testingFiles != null){
                int testIndex = (int)(testingFiles.length*0.8);
                for (; testIndex < testingFiles.length; testIndex++){
                    String text = Files.readFromFile(new File(classifyDirectory, testingFiles[testIndex]),
                            "ISO-8859-1");
                    System.out.print("Testing " + testingFiles[testIndex] + " ");
                    Classification classification = new Classification(categoryName);
                    Classified<CharSequence> classified = new Classified<>(text, classification);
                    evaluator.handle(classified);
                    JointClassification jc = compiledClassifier.classify(text);
                    String bestCategory = jc.bestCategory();
                    String details = jc.toString();
                    System.out.println("Got best category of: " + bestCategory);
                    System.out.print(details);
                    System.out.println("---------------");
                }
            }
        }
    }
}
