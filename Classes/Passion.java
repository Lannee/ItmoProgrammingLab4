package Classes;

public enum Passion {
    PASTA_BUSINESS("макаронне дело");

    private final String translation;

    Passion(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
