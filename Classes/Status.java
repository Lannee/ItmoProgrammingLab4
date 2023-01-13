package Classes;

public enum Status {
    WEIGHTLESSNESS("невесомость"),
    CONFUSION("смятение");

    private String translation;

    Status(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
