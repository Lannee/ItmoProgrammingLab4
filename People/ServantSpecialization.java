package People;

public enum ServantSpecialization {
    MAID("горничные"),
    HANDMAID("служанки"),
    LAUNDRESS("прачки"),
    PORTER("швейцары"),
    POLISHER("политеры"),
    COOK("повара");

    private final String translation;

    ServantSpecialization(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
