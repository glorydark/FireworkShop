package glorydark.fireworkshop.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.ItemFirework;
import cn.nukkit.level.Location;
import cn.nukkit.level.ParticleEffect;
import cn.nukkit.level.Position;
import cn.nukkit.level.particle.FlameParticle;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.DyeColor;
import cn.nukkit.utils.TextFormat;
import glorydark.fireworkshop.MainClass;
import glorydark.fireworkshop.api.CreateFireworkApi;
import glorydark.fireworkshop.gui.GuiMain;
import glorydark.fireworkshop.gui.guitype;
import glorydark.fireworkshop.utils.config;
import me.onebone.economyapi.EconomyAPI;
import net.player.api.Point;

import java.util.ArrayList;
import java.util.List;

public class EventListener implements Listener {
    @EventHandler
    public void PlayerFormRespondedEvent(PlayerFormRespondedEvent event){
        Player p = event.getPlayer();
        FormWindow window = event.getWindow();
        if (p == null || window == null) {
            return;
        }
        if(event.getWindow().getResponse() == null){ return; }
        if(GuiMain.UI_CACHE.containsKey(p)) {
            if (GuiMain.UI_CACHE.get(p).containsKey(event.getFormID())) {
                this.SimpleOnClick(p, (FormWindowSimple) window, GuiMain.UI_CACHE.get(p).get(event.getFormID()));
                GuiMain.UI_CACHE.get(p).remove(event.getFormID());
            }
        }
    }

    @EventHandler
    public void PlayerCommandProcessedEvent(PlayerCommandPreprocessEvent event){
        if(MainClass.playerfireworkcache.containsKey(event.getPlayer()) && event.getMessage().contains("/fs") && !event.getMessage().contains("/fsaccept")){
            event.getPlayer().sendMessage("??????????????????????????????????????????????????????????????????");
            event.setCancelled();
        }
    }

    private void SimpleOnClick(Player p, FormWindowSimple simple, guitype type){
        FormResponseSimple responseSimple = simple.getResponse();
        if(responseSimple == null){ return; }
        switch (type) {
            case MainMenu:
                GuiMain.createInformationMenu(responseSimple.getClickedButton().getText(), p);
                break;
            case InformationMenu:
                if (responseSimple.getClickedButtonId() == 0) { //????????????
                    Config GoodsCfg = config.getConfig("shops.yml");
                    List<String> CostMoney = GoodsCfg.getStringList(simple.getTitle() + ".????????????");
                    double CoinCost = 0d;
                    double PointCost = 0d;
                    boolean payment = true;
                    for (String s : CostMoney) {
                        if (s.split(":").length == 2) {
                            String[] split = s.split(":");
                            if (split[0].equals("??????")) {
                                PointCost += Double.parseDouble(split[1]);
                            }
                            if (split[0].equals("??????")) {
                                CoinCost += Double.parseDouble(split[1]);
                            }
                        }
                    }
                    if (EconomyAPI.getInstance().myMoney(p) < CoinCost) {
                        payment = false;
                    }
                    if (MainClass.bool) {
                        if (Point.getPoint(p.getUniqueId()) < CoinCost) {
                            payment = false;
                        }
                    }
                    if (payment) {
                        EconomyAPI.getInstance().reduceMoney(p, CoinCost);
                        if (MainClass.bool) {
                            Point.reducePoint(p, PointCost);
                        }
                        p.sendMessage("??????????????????????????????: %coin% ??? ?????? %point%".replace("%coin%", Double.toString(CoinCost)).replace("%point%", Double.toString(PointCost)));
                        String GoodsName = simple.getTitle();
                        int BuyType = GoodsCfg.getInt(GoodsName + ".??????");
                        if (BuyType == 0) {
                            List<Double> position = GoodsCfg.get(GoodsName + ".??????", new ArrayList<>());
                            String WorldName = GoodsCfg.getString(GoodsName + ".????????????");
                            List<String> FireworkType = GoodsCfg.get(GoodsName + ".????????????", new ArrayList<>());
                            boolean state = GoodsCfg.getBoolean(GoodsName + ".??????????????????");
                            int time = GoodsCfg.getInt(GoodsName + ".????????????");
                            Position pos = new Position(position.get(0), position.get(1), position.get(2), p.getServer().getLevelByName(WorldName));
                            DyeColor color = config.getColorByString(FireworkType.get(1));
                            ItemFirework.FireworkExplosion.ExplosionType explosionType = config.getExplosionTypeByString(FireworkType.get(0));
                            for (int i = 0; i < time; i++) {
                                if (!state) {
                                    CreateFireworkApi.spawnFirework(pos, color, explosionType);
                                } else {
                                    CreateFireworkApi.spawnFirework(p.getPosition(), color, explosionType);
                                }
                            }
                            p.getServer().getConsoleSender().sendMessage("?????????" + p.getName() + "?????????" + pos + "????????????????????????");
                            return;
                        }
                        if (BuyType == 1) {
                            String TemplateName = GoodsCfg.getString(GoodsName + ".??????");
                            boolean state = GoodsCfg.getBoolean(GoodsName + ".??????????????????", true);
                            if (state) {
                                for (Player player : p.getServer().getOnlinePlayers().values()) {
                                    p.sendTitle(TextFormat.GOLD + "[??????]" + p.getName() + "???????????????", "??????/fsaccept ??????????????????", 10, 40, 10);
                                }
                                MainClass.arrivepos = p.getPosition();
                                config.parseTemplate(TemplateName, p, p.getPosition());
                            } else {
                                for (Player player : p.getServer().getOnlinePlayers().values()) {
                                    p.sendTitle(TextFormat.GOLD + "[??????]" + p.getName() + "???????????????", "??????/fsaccept ??????????????????", 10, 40, 10);
                                }
                                List<Double> position = GoodsCfg.get(GoodsName + ".??????", new ArrayList<>());
                                String WorldName = GoodsCfg.getString(GoodsName + ".????????????");
                                Position pos = new Position(position.get(0), position.get(1), position.get(2), p.getServer().getLevelByName(WorldName));
                                MainClass.arrivepos = pos;
                                config.parseTemplate(TemplateName, p, pos);
                                p.teleportImmediate(new Location(pos.x, pos.y, pos.z, pos.level));
                            }
                            return;
                        }
                    } else {
                        p.sendMessage("?????????????????????");
                        return;
                    }
                }
                if (responseSimple.getClickedButtonId() == 1) { //??????
                    GuiMain.createMainMenu(p);
                }
                break;
            default:
                break;
        }
    }

    public static void CreateCircleParticle(int type, Position pos, int r){
        switch(type) {
            case 0:
                ParticleEffect particleeffect = ParticleEffect.BLUE_FLAME;
                for(int angle = 0;angle < 720;angle++){
                    double x1 = pos.x + r * Math.cos(angle*3.14/180);
                    double z1 = pos.z + r * Math.sin(angle*3.14/180);
                    pos.getLevel().addParticleEffect(new Position(x1,pos.y,z1),particleeffect);
                }
                break;
        }
    }

    @EventHandler
    public static void Move(PlayerMoveEvent event){
        if(MainClass.playerfireworkcache.containsKey(event.getPlayer())) {
            Player p = event.getPlayer();
            p.getLevel().addParticle(new FlameParticle(p.getPosition()), p);
        }
    }
}
