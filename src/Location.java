public enum Location {
    HOME, MEETING, LIBRARY, ER, CS, SUBWAY;

    public static Location get(int id) {
        switch (id) {
            case 0: return HOME;
            case 1: return MEETING;
            case 2: return LIBRARY;
            case 4: return CS;
            case 6: return SUBWAY;
            default: return ER;
        }
    }
}
