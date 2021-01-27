package net.morimori.mildomintegration.mildom;

public enum Nanika {
    N1008("1008", 799987854721679380l),
    N1007("1007", 799987855769731094l),
    N1006("1006", 799987857267490856l),
    N1005("1005", 799987858390646794l),
    N1004("1004", 799987859700318218l),
    N1003("1003", 799987860878262282l),
    N1002("1002", 799987862187540480l),
    N1001("1001", 799987863469948958l),
    N1012("1012", 799987864959713290l),
    N1011("1011", 799987866444627968l),
    N1010("1010", 799987867770159124l),
    N1009("1009", 799987868872605727l);

    private final String name;
    private final long demid;

    private Nanika(String name, long id) {
        this.name = name;
        this.demid = id;
    }

    public String getName() {
        return name;
    }

    public long getDiscordEmojiID() {
        return demid;
    }

    public String getStr(){
        return String.format("[/%s]",name);
    }

    public String getEmojiStr(){
        return "<:N"+name+":"+demid+">";
    }
}
