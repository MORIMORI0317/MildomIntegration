package net.pmtf.mildomintegration.mildom;

import com.google.gson.JsonObject;
import net.pmtf.mildomintegration.Config;
import net.pmtf.mildomintegration.util.URLUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MildomLiveRanking {
    private static final String SERCHU_URL = "https://cloudac.mildom.com/nonolive/gappserv/channel/detailList?channel_key=all_game&channel_tag=all&page=%s&limit=1000&__guest_id=pc&__platform=web&__la=ja";
    private static long lastRankingSearch;
    public static boolean serching;
    private static MildomLiveRankingData rkData = null;
    public static boolean err = false;

    public static void tick() {
        long cl = Config.searchCoolDown;
        if (cl >= 0 && (lastRankingSearch == 0 || System.currentTimeMillis() - lastRankingSearch > cl) && !serching) {
            serchid();
        }
    }

    public static void serchid() {
        serching = true;
        err = false;
        lastRankingSearch = System.currentTimeMillis();
        Thread te = new Thread(() -> {
            try {
                rkData = MildomLiveRanking.search();
            } catch (Exception ex) {
                ex.printStackTrace();
                err = true;
            } finally {
                serching = false;
            }
        });
        te.start();
    }

    public static MildomLiveRankingData getRankData() {
        return rkData;
    }

    private static int lastRank = 0;

    public static MildomLiveRankingData search() throws Exception {
        String roomID = Config.RoomID;
        boolean canS = true;
        int pageCont = 1;
        Map<Integer, Integer> LIVES = new HashMap<>();
        while (canS) {
            JsonObject jo = URLUtils.getURLJsonResponse(String.format(SERCHU_URL, pageCont)).getAsJsonObject("body");
            if (jo.getAsJsonArray("models") != null) {
                jo.getAsJsonArray("models").forEach(n -> {
                    try {
                        LIVES.put(n.getAsJsonObject().get("user_id").getAsInt(), n.getAsJsonObject().get("viewers").getAsInt());
                    } catch (Exception ignored) {
                    }
                });
            } else {
                canS = false;
            }
            pageCont++;
        }
        List<Integer> ranks = LIVES.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList());

        int rkCont = 1;
        for (int i = 0; i < ranks.size(); i++) {
            int rkroomID = ranks.get(ranks.size() - i - 1);
            if (rkroomID == Integer.parseInt(roomID)) {
                int fluctuation = lastRank == 0 ? 0 : rkCont - lastRank;
                lastRank = rkCont;
                return new MildomLiveRankingData(rkCont, ranks.size(), fluctuation);
            }
            rkCont++;
        }
        return null;
    }

    public static class MildomLiveRankingData {
        private final int ranking;
        private final int serchMax;
        private final int fluctuation;

        private MildomLiveRankingData(int ranking, int serchMax, int fluctuation) {
            this.ranking = ranking;
            this.serchMax = serchMax;
            this.fluctuation = fluctuation;
        }

        public int getFluctuation() {
            return fluctuation;
        }

        public int getRanking() {
            return ranking;
        }

        public int getSerchMax() {
            return serchMax;
        }

        @Override
        public String toString() {
            return "MildomLiveRankingData{" +
                    "ranking=" + ranking +
                    ", serchMax=" + serchMax +
                    ", fluctuation=" + fluctuation +
                    '}';
        }
    }
}
