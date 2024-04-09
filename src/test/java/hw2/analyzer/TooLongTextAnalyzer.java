package hw2.analyzer;

public class TooLongTextAnalyzer implements TextAnalyzer {

    private final int maxLen;

    public TooLongTextAnalyzer(int maxLen) {
        this.maxLen = maxLen;
    }

    @Override
    public Label processText(String text) {
        return text.length() <= maxLen? Label.OK: Label.TOO_LONG;
    }
}
