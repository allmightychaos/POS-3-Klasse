import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


@XmlRootElement(name = "analysis")
public class TextAnalysis {
    private List<String> texts = new ArrayList<>();
    private List<LetterCount> letterCounts = new ArrayList<>();

    public TextAnalysis() {
    }

    public List<String> getTexts() {
        return texts;
    }

    @XmlElement(name = "text")
    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    public List<LetterCount> getLetterCounts() {
        return letterCounts;
    }

    @XmlElement(name = "letterCount")
    public void setLetterCounts(List<LetterCount> letterCounts) {
        this.letterCounts = letterCounts;
    }

    public void addText(String text) {
        this.texts.add(text);
    }

    public void addLetterCount(LetterCount letterCount) {
        this.letterCounts.add(letterCount);
    }
}
