import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "letterCount")
public class LetterCount {
    private char letter;
    private int count;

    public LetterCount() {
    }

    public LetterCount(char letter, int count) {
        this.letter = letter;
        this.count = count;
    }

    public char getLetter() {
        return letter;
    }

    @XmlElement(name = "letter")
    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getCount() {
        return count;
    }

    @XmlElement(name = "count")
    public void setCount(int count) {
        this.count = count;
    }
}