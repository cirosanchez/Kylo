package me.cirosanchez.kylo.util;

public enum Color {
    WHITE(1118369244724351036L),
    BLACK(1118368992915103744L),
    PINK(1118368724077002774L),
    YELLOW(1118368625284362341L),
    ORANGE(1118368529167683614L),
    GREEN(1118368353984204820L),
    BLURPLE(1118368226812899428L),
    CYAN(1118368146995286057L),
    BLUE(1118368104821559399L),
    RED(1118367927423483945L);


    private Long l;
    Color(long l) {
        this.l = l;
    }

    public Long getLong(){
        return l;
    }


}
