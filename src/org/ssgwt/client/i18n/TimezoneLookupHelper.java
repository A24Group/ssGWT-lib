/**
 * Copyright 2012 A24Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ssgwt.client.i18n;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.TimeZoneInfo;

/**
 * The time zone lookup helper to enable time zone lookup using full timezone names
 *
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  16 July 2016
 */
public class TimezoneLookupHelper {

    /**
     * The instance of the time zone lookup helper
     */
    private static TimezoneLookupHelper instance;

    /**
     * The map used to do the lookup of time zone
     */
    private HashMap<String, TimeZoneInfo> timeZoneInfos = new HashMap<String, TimeZoneInfo>();

    /**
     * Class constructor
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     */
    private TimezoneLookupHelper() {
        initLookup();
    }

    /**
     * Adds a time zone info item to the lookup hash map
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @param timezoneInfo - The time zone info details as retrieve from the SSTimeZoneConstants
     */
    private void addTimeZoneInfo(String timezoneInfo) {
        TimeZoneInfo x = TimeZoneInfo.buildTimeZoneData(timezoneInfo);
        timeZoneInfos.put(x.getID(), x);
    }

    /**
     * Retrieves the instance of the time zone lookup helper
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return The instance of the time zone lookup helper
     */
    public static TimezoneLookupHelper getInstance() {
        if (TimezoneLookupHelper.instance == null) {
            TimezoneLookupHelper.instance = new TimezoneLookupHelper();
        }

        return TimezoneLookupHelper.instance;
    }

    /**
     * Retrieves a time zone for a give time zone id
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @param id - The time zone id like Africa/Johannesburg
     *
     * @return The time zone for the given id
     */
    public TimeZoneInfo getTimezone(String id) {
        if (timeZoneInfos.containsKey(id)) {
            return timeZoneInfos.get(id);
        }
        return null;
    }

    /**
     * Initializes the time zone lookup hash map
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     */
    private void initLookup() {
        SSTimeZoneConstants timeZoneConstants = GWT.create(SSTimeZoneConstants.class);
        addTimeZoneInfo(timeZoneConstants.africaAbidjan());
        addTimeZoneInfo(timeZoneConstants.africaAccra());
        addTimeZoneInfo(timeZoneConstants.africaAddisAbaba());
        addTimeZoneInfo(timeZoneConstants.africaAlgiers());
        addTimeZoneInfo(timeZoneConstants.africaAsmera());
        addTimeZoneInfo(timeZoneConstants.africaBamako());
        addTimeZoneInfo(timeZoneConstants.africaBangui());
        addTimeZoneInfo(timeZoneConstants.africaBanjul());
        addTimeZoneInfo(timeZoneConstants.africaBissau());
        addTimeZoneInfo(timeZoneConstants.africaBlantyre());
        addTimeZoneInfo(timeZoneConstants.africaBrazzaville());
        addTimeZoneInfo(timeZoneConstants.africaBujumbura());
        addTimeZoneInfo(timeZoneConstants.africaCairo());
        addTimeZoneInfo(timeZoneConstants.africaCasablanca());
        addTimeZoneInfo(timeZoneConstants.africaCeuta());
        addTimeZoneInfo(timeZoneConstants.africaConakry());
        addTimeZoneInfo(timeZoneConstants.africaDakar());
        addTimeZoneInfo(timeZoneConstants.africaDaresSalaam());
        addTimeZoneInfo(timeZoneConstants.africaDjibouti());
        addTimeZoneInfo(timeZoneConstants.africaDouala());
        addTimeZoneInfo(timeZoneConstants.africaElAaiun());
        addTimeZoneInfo(timeZoneConstants.africaFreetown());
        addTimeZoneInfo(timeZoneConstants.africaGaborone());
        addTimeZoneInfo(timeZoneConstants.africaHarare());
        addTimeZoneInfo(timeZoneConstants.africaJohannesburg());
        addTimeZoneInfo(timeZoneConstants.africaKampala());
        addTimeZoneInfo(timeZoneConstants.africaKhartoum());
        addTimeZoneInfo(timeZoneConstants.africaKigali());
        addTimeZoneInfo(timeZoneConstants.africaKinshasa());
        addTimeZoneInfo(timeZoneConstants.africaLagos());
        addTimeZoneInfo(timeZoneConstants.africaLibreville());
        addTimeZoneInfo(timeZoneConstants.africaLome());
        addTimeZoneInfo(timeZoneConstants.africaLuanda());
        addTimeZoneInfo(timeZoneConstants.africaLubumbashi());
        addTimeZoneInfo(timeZoneConstants.africaLusaka());
        addTimeZoneInfo(timeZoneConstants.africaMalabo());
        addTimeZoneInfo(timeZoneConstants.africaMaputo());
        addTimeZoneInfo(timeZoneConstants.africaMaseru());
        addTimeZoneInfo(timeZoneConstants.africaMbabane());
        addTimeZoneInfo(timeZoneConstants.africaMogadishu());
        addTimeZoneInfo(timeZoneConstants.africaMonrovia());
        addTimeZoneInfo(timeZoneConstants.africaNairobi());
        addTimeZoneInfo(timeZoneConstants.africaNdjamena());
        addTimeZoneInfo(timeZoneConstants.africaNiamey());
        addTimeZoneInfo(timeZoneConstants.africaNouakchott());
        addTimeZoneInfo(timeZoneConstants.africaOuagadougou());
        addTimeZoneInfo(timeZoneConstants.africaPortoNovo());
        addTimeZoneInfo(timeZoneConstants.africaSaoTome());
        addTimeZoneInfo(timeZoneConstants.africaTripoli());
        addTimeZoneInfo(timeZoneConstants.africaTunis());
        addTimeZoneInfo(timeZoneConstants.africaWindhoek());
        addTimeZoneInfo(timeZoneConstants.americaAdak());
        addTimeZoneInfo(timeZoneConstants.americaAnchorage());
        addTimeZoneInfo(timeZoneConstants.americaAnguilla());
        addTimeZoneInfo(timeZoneConstants.americaAntigua());
        addTimeZoneInfo(timeZoneConstants.americaAraguaina());
        addTimeZoneInfo(timeZoneConstants.americaArgentinaLaRioja());
        addTimeZoneInfo(timeZoneConstants.americaArgentinaRioGallegos());
        addTimeZoneInfo(timeZoneConstants.americaArgentinaSanJuan());
        addTimeZoneInfo(timeZoneConstants.americaArgentinaTucuman());
        addTimeZoneInfo(timeZoneConstants.americaArgentinaUshuaia());
        addTimeZoneInfo(timeZoneConstants.americaAruba());
        addTimeZoneInfo(timeZoneConstants.americaAsuncion());
        addTimeZoneInfo(timeZoneConstants.americaBahia());
        addTimeZoneInfo(timeZoneConstants.americaBarbados());
        addTimeZoneInfo(timeZoneConstants.americaBelem());
        addTimeZoneInfo(timeZoneConstants.americaBelize());
        addTimeZoneInfo(timeZoneConstants.americaBlancSablon());
        addTimeZoneInfo(timeZoneConstants.americaBoaVista());
        addTimeZoneInfo(timeZoneConstants.americaBogota());
        addTimeZoneInfo(timeZoneConstants.americaBoise());
        addTimeZoneInfo(timeZoneConstants.americaBuenosAires());
        addTimeZoneInfo(timeZoneConstants.americaCambridgeBay());
        addTimeZoneInfo(timeZoneConstants.americaCampoGrande());
        addTimeZoneInfo(timeZoneConstants.americaCancun());
        addTimeZoneInfo(timeZoneConstants.americaCaracas());
        addTimeZoneInfo(timeZoneConstants.americaCatamarca());
        addTimeZoneInfo(timeZoneConstants.americaCayenne());
        addTimeZoneInfo(timeZoneConstants.americaCayman());
        addTimeZoneInfo(timeZoneConstants.americaChicago());
        addTimeZoneInfo(timeZoneConstants.americaChihuahua());
        addTimeZoneInfo(timeZoneConstants.americaCoralHarbour());
        addTimeZoneInfo(timeZoneConstants.americaCordoba());
        addTimeZoneInfo(timeZoneConstants.americaCostaRica());
        addTimeZoneInfo(timeZoneConstants.americaCuiaba());
        addTimeZoneInfo(timeZoneConstants.americaCuracao());
        addTimeZoneInfo(timeZoneConstants.americaDanmarkshavn());
        addTimeZoneInfo(timeZoneConstants.americaDawson());
        addTimeZoneInfo(timeZoneConstants.americaDawsonCreek());
        addTimeZoneInfo(timeZoneConstants.americaDenver());
        addTimeZoneInfo(timeZoneConstants.americaDetroit());
        addTimeZoneInfo(timeZoneConstants.americaDominica());
        addTimeZoneInfo(timeZoneConstants.americaEdmonton());
        addTimeZoneInfo(timeZoneConstants.americaEirunepe());
        addTimeZoneInfo(timeZoneConstants.americaElSalvador());
        addTimeZoneInfo(timeZoneConstants.americaFortaleza());
        addTimeZoneInfo(timeZoneConstants.americaGlaceBay());
        addTimeZoneInfo(timeZoneConstants.americaGodthab());
        addTimeZoneInfo(timeZoneConstants.americaGooseBay());
        addTimeZoneInfo(timeZoneConstants.americaGrandTurk());
        addTimeZoneInfo(timeZoneConstants.americaGrenada());
        addTimeZoneInfo(timeZoneConstants.americaGuadeloupe());
        addTimeZoneInfo(timeZoneConstants.americaGuatemala());
        addTimeZoneInfo(timeZoneConstants.americaGuayaquil());
        addTimeZoneInfo(timeZoneConstants.americaGuyana());
        addTimeZoneInfo(timeZoneConstants.americaHalifax());
        addTimeZoneInfo(timeZoneConstants.americaHavana());
        addTimeZoneInfo(timeZoneConstants.americaHermosillo());
        addTimeZoneInfo(timeZoneConstants.americaIndianaKnox());
        addTimeZoneInfo(timeZoneConstants.americaIndianaMarengo());
        addTimeZoneInfo(timeZoneConstants.americaIndianaPetersburg());
        addTimeZoneInfo(timeZoneConstants.americaIndianapolis());
        addTimeZoneInfo(timeZoneConstants.americaIndianaVevay());
        addTimeZoneInfo(timeZoneConstants.americaIndianaVincennes());
        addTimeZoneInfo(timeZoneConstants.americaInuvik());
        addTimeZoneInfo(timeZoneConstants.americaIqaluit());
        addTimeZoneInfo(timeZoneConstants.americaJamaica());
        addTimeZoneInfo(timeZoneConstants.americaJujuy());
        addTimeZoneInfo(timeZoneConstants.americaJuneau());
        addTimeZoneInfo(timeZoneConstants.americaKentuckyMonticello());
        addTimeZoneInfo(timeZoneConstants.americaLaPaz());
        addTimeZoneInfo(timeZoneConstants.americaLima());
        addTimeZoneInfo(timeZoneConstants.americaLosAngeles());
        addTimeZoneInfo(timeZoneConstants.americaLouisville());
        addTimeZoneInfo(timeZoneConstants.americaMaceio());
        addTimeZoneInfo(timeZoneConstants.americaManagua());
        addTimeZoneInfo(timeZoneConstants.americaManaus());
        addTimeZoneInfo(timeZoneConstants.americaMartinique());
        addTimeZoneInfo(timeZoneConstants.americaMazatlan());
        addTimeZoneInfo(timeZoneConstants.americaMendoza());
        addTimeZoneInfo(timeZoneConstants.americaMenominee());
        addTimeZoneInfo(timeZoneConstants.americaMerida());
        addTimeZoneInfo(timeZoneConstants.americaMexicoCity());
        addTimeZoneInfo(timeZoneConstants.americaMiquelon());
        addTimeZoneInfo(timeZoneConstants.americaMoncton());
        addTimeZoneInfo(timeZoneConstants.americaMonterrey());
        addTimeZoneInfo(timeZoneConstants.americaMontevideo());
        addTimeZoneInfo(timeZoneConstants.americaMontreal());
        addTimeZoneInfo(timeZoneConstants.americaMontserrat());
        addTimeZoneInfo(timeZoneConstants.americaNassau());
        addTimeZoneInfo(timeZoneConstants.americaNewYork());
        addTimeZoneInfo(timeZoneConstants.americaNipigon());
        addTimeZoneInfo(timeZoneConstants.americaNome());
        addTimeZoneInfo(timeZoneConstants.americaNoronha());
        addTimeZoneInfo(timeZoneConstants.americaNorthDakotaCenter());
        addTimeZoneInfo(timeZoneConstants.americaNorthDakotaNewSalem());
        addTimeZoneInfo(timeZoneConstants.americaPanama());
        addTimeZoneInfo(timeZoneConstants.americaPangnirtung());
        addTimeZoneInfo(timeZoneConstants.americaParamaribo());
        addTimeZoneInfo(timeZoneConstants.americaPhoenix());
        addTimeZoneInfo(timeZoneConstants.americaPortauPrince());
        addTimeZoneInfo(timeZoneConstants.americaPortofSpain());
        addTimeZoneInfo(timeZoneConstants.americaPortoVelho());
        addTimeZoneInfo(timeZoneConstants.americaPuertoRico());
        addTimeZoneInfo(timeZoneConstants.americaRainyRiver());
        addTimeZoneInfo(timeZoneConstants.americaRankinInlet());
        addTimeZoneInfo(timeZoneConstants.americaRecife());
        addTimeZoneInfo(timeZoneConstants.americaRegina());
        addTimeZoneInfo(timeZoneConstants.americaRioBranco());
        addTimeZoneInfo(timeZoneConstants.americaSantiago());
        addTimeZoneInfo(timeZoneConstants.americaSantoDomingo());
        addTimeZoneInfo(timeZoneConstants.americaSaoPaulo());
        addTimeZoneInfo(timeZoneConstants.americaScoresbysund());
        addTimeZoneInfo(timeZoneConstants.americaShiprock());
        addTimeZoneInfo(timeZoneConstants.americaStJohns());
        addTimeZoneInfo(timeZoneConstants.americaStKitts());
        addTimeZoneInfo(timeZoneConstants.americaStLucia());
        addTimeZoneInfo(timeZoneConstants.americaStThomas());
        addTimeZoneInfo(timeZoneConstants.americaStVincent());
        addTimeZoneInfo(timeZoneConstants.americaSwiftCurrent());
        addTimeZoneInfo(timeZoneConstants.americaTegucigalpa());
        addTimeZoneInfo(timeZoneConstants.americaThule());
        addTimeZoneInfo(timeZoneConstants.americaThunderBay());
        addTimeZoneInfo(timeZoneConstants.americaTijuana());
        addTimeZoneInfo(timeZoneConstants.americaToronto());
        addTimeZoneInfo(timeZoneConstants.americaTortola());
        addTimeZoneInfo(timeZoneConstants.americaVancouver());
        addTimeZoneInfo(timeZoneConstants.americaWhitehorse());
        addTimeZoneInfo(timeZoneConstants.americaWinnipeg());
        addTimeZoneInfo(timeZoneConstants.americaYakutat());
        addTimeZoneInfo(timeZoneConstants.americaYellowknife());
        addTimeZoneInfo(timeZoneConstants.antarcticaCasey());
        addTimeZoneInfo(timeZoneConstants.antarcticaDavis());
        addTimeZoneInfo(timeZoneConstants.antarcticaDumontDUrville());
        addTimeZoneInfo(timeZoneConstants.antarcticaMawson());
        addTimeZoneInfo(timeZoneConstants.antarcticaMcMurdo());
        addTimeZoneInfo(timeZoneConstants.antarcticaPalmer());
        addTimeZoneInfo(timeZoneConstants.antarcticaRothera());
        addTimeZoneInfo(timeZoneConstants.antarcticaSyowa());
        addTimeZoneInfo(timeZoneConstants.antarcticaVostok());
        addTimeZoneInfo(timeZoneConstants.asiaAden());
        addTimeZoneInfo(timeZoneConstants.asiaAlmaty());
        addTimeZoneInfo(timeZoneConstants.asiaAmman());
        addTimeZoneInfo(timeZoneConstants.asiaAnadyr());
        addTimeZoneInfo(timeZoneConstants.asiaAqtau());
        addTimeZoneInfo(timeZoneConstants.asiaAqtobe());
        addTimeZoneInfo(timeZoneConstants.asiaAshgabat());
        addTimeZoneInfo(timeZoneConstants.asiaBaghdad());
        addTimeZoneInfo(timeZoneConstants.asiaBahrain());
        addTimeZoneInfo(timeZoneConstants.asiaBaku());
        addTimeZoneInfo(timeZoneConstants.asiaBangkok());
        addTimeZoneInfo(timeZoneConstants.asiaBeirut());
        addTimeZoneInfo(timeZoneConstants.asiaBishkek());
        addTimeZoneInfo(timeZoneConstants.asiaBrunei());
        addTimeZoneInfo(timeZoneConstants.asiaCalcutta());
        addTimeZoneInfo(timeZoneConstants.asiaChoibalsan());
        addTimeZoneInfo(timeZoneConstants.asiaChongqing());
        addTimeZoneInfo(timeZoneConstants.asiaColombo());
        addTimeZoneInfo(timeZoneConstants.asiaDamascus());
        addTimeZoneInfo(timeZoneConstants.asiaDhaka());
        addTimeZoneInfo(timeZoneConstants.asiaDili());
        addTimeZoneInfo(timeZoneConstants.asiaDubai());
        addTimeZoneInfo(timeZoneConstants.asiaDushanbe());
        addTimeZoneInfo(timeZoneConstants.asiaGaza());
        addTimeZoneInfo(timeZoneConstants.asiaHarbin());
        addTimeZoneInfo(timeZoneConstants.asiaHongKong());
        addTimeZoneInfo(timeZoneConstants.asiaHovd());
        addTimeZoneInfo(timeZoneConstants.asiaIrkutsk());
        addTimeZoneInfo(timeZoneConstants.asiaJakarta());
        addTimeZoneInfo(timeZoneConstants.asiaJayapura());
        addTimeZoneInfo(timeZoneConstants.asiaJerusalem());
        addTimeZoneInfo(timeZoneConstants.asiaKabul());
        addTimeZoneInfo(timeZoneConstants.asiaKamchatka());
        addTimeZoneInfo(timeZoneConstants.asiaKarachi());
        addTimeZoneInfo(timeZoneConstants.asiaKashgar());
        addTimeZoneInfo(timeZoneConstants.asiaKatmandu());
        addTimeZoneInfo(timeZoneConstants.asiaKrasnoyarsk());
        addTimeZoneInfo(timeZoneConstants.asiaKualaLumpur());
        addTimeZoneInfo(timeZoneConstants.asiaKuching());
        addTimeZoneInfo(timeZoneConstants.asiaKuwait());
        addTimeZoneInfo(timeZoneConstants.asiaMacau());
        addTimeZoneInfo(timeZoneConstants.asiaMagadan());
        addTimeZoneInfo(timeZoneConstants.asiaMakassar());
        addTimeZoneInfo(timeZoneConstants.asiaManila());
        addTimeZoneInfo(timeZoneConstants.asiaMuscat());
        addTimeZoneInfo(timeZoneConstants.asiaNicosia());
        addTimeZoneInfo(timeZoneConstants.asiaNovosibirsk());
        addTimeZoneInfo(timeZoneConstants.asiaOmsk());
        addTimeZoneInfo(timeZoneConstants.asiaOral());
        addTimeZoneInfo(timeZoneConstants.asiaPhnomPenh());
        addTimeZoneInfo(timeZoneConstants.asiaPontianak());
        addTimeZoneInfo(timeZoneConstants.asiaPyongyang());
        addTimeZoneInfo(timeZoneConstants.asiaQatar());
        addTimeZoneInfo(timeZoneConstants.asiaQyzylorda());
        addTimeZoneInfo(timeZoneConstants.asiaRangoon());
        addTimeZoneInfo(timeZoneConstants.asiaRiyadh());
        addTimeZoneInfo(timeZoneConstants.asiaSaigon());
        addTimeZoneInfo(timeZoneConstants.asiaSakhalin());
        addTimeZoneInfo(timeZoneConstants.asiaSamarkand());
        addTimeZoneInfo(timeZoneConstants.asiaSeoul());
        addTimeZoneInfo(timeZoneConstants.asiaShanghai());
        addTimeZoneInfo(timeZoneConstants.asiaSingapore());
        addTimeZoneInfo(timeZoneConstants.asiaTaipei());
        addTimeZoneInfo(timeZoneConstants.asiaTashkent());
        addTimeZoneInfo(timeZoneConstants.asiaTbilisi());
        addTimeZoneInfo(timeZoneConstants.asiaTehran());
        addTimeZoneInfo(timeZoneConstants.asiaThimphu());
        addTimeZoneInfo(timeZoneConstants.asiaTokyo());
        addTimeZoneInfo(timeZoneConstants.asiaUlaanbaatar());
        addTimeZoneInfo(timeZoneConstants.asiaUrumqi());
        addTimeZoneInfo(timeZoneConstants.asiaVientiane());
        addTimeZoneInfo(timeZoneConstants.asiaVladivostok());
        addTimeZoneInfo(timeZoneConstants.asiaYakutsk());
        addTimeZoneInfo(timeZoneConstants.asiaYekaterinburg());
        addTimeZoneInfo(timeZoneConstants.asiaYerevan());
        addTimeZoneInfo(timeZoneConstants.atlanticAzores());
        addTimeZoneInfo(timeZoneConstants.atlanticBermuda());
        addTimeZoneInfo(timeZoneConstants.atlanticCanary());
        addTimeZoneInfo(timeZoneConstants.atlanticCapeVerde());
        addTimeZoneInfo(timeZoneConstants.atlanticFaeroe());
        addTimeZoneInfo(timeZoneConstants.atlanticMadeira());
        addTimeZoneInfo(timeZoneConstants.atlanticReykjavik());
        addTimeZoneInfo(timeZoneConstants.atlanticSouthGeorgia());
        addTimeZoneInfo(timeZoneConstants.atlanticStanley());
        addTimeZoneInfo(timeZoneConstants.atlanticStHelena());
        addTimeZoneInfo(timeZoneConstants.australiaAdelaide());
        addTimeZoneInfo(timeZoneConstants.australiaBrisbane());
        addTimeZoneInfo(timeZoneConstants.australiaBrokenHill());
        addTimeZoneInfo(timeZoneConstants.australiaCurrie());
        addTimeZoneInfo(timeZoneConstants.australiaDarwin());
        addTimeZoneInfo(timeZoneConstants.australiaEucla());
        addTimeZoneInfo(timeZoneConstants.australiaHobart());
        addTimeZoneInfo(timeZoneConstants.australiaLindeman());
        addTimeZoneInfo(timeZoneConstants.australiaLordHowe());
        addTimeZoneInfo(timeZoneConstants.australiaMelbourne());
        addTimeZoneInfo(timeZoneConstants.australiaPerth());
        addTimeZoneInfo(timeZoneConstants.australiaSydney());
        addTimeZoneInfo(timeZoneConstants.europeAmsterdam());
        addTimeZoneInfo(timeZoneConstants.europeAndorra());
        addTimeZoneInfo(timeZoneConstants.europeAthens());
        addTimeZoneInfo(timeZoneConstants.europeBelgrade());
        addTimeZoneInfo(timeZoneConstants.europeBerlin());
        addTimeZoneInfo(timeZoneConstants.europeBratislava());
        addTimeZoneInfo(timeZoneConstants.europeBrussels());
        addTimeZoneInfo(timeZoneConstants.europeBucharest());
        addTimeZoneInfo(timeZoneConstants.europeBudapest());
        addTimeZoneInfo(timeZoneConstants.europeChisinau());
        addTimeZoneInfo(timeZoneConstants.europeCopenhagen());
        addTimeZoneInfo(timeZoneConstants.europeDublin());
        addTimeZoneInfo(timeZoneConstants.europeGibraltar());
        addTimeZoneInfo(timeZoneConstants.europeHelsinki());
        addTimeZoneInfo(timeZoneConstants.europeIstanbul());
        addTimeZoneInfo(timeZoneConstants.europeKaliningrad());
        addTimeZoneInfo(timeZoneConstants.europeKiev());
        addTimeZoneInfo(timeZoneConstants.europeLisbon());
        addTimeZoneInfo(timeZoneConstants.europeLjubljana());
        addTimeZoneInfo(timeZoneConstants.europeLondon());
        addTimeZoneInfo(timeZoneConstants.europeLuxembourg());
        addTimeZoneInfo(timeZoneConstants.europeMadrid());
        addTimeZoneInfo(timeZoneConstants.europeMalta());
        addTimeZoneInfo(timeZoneConstants.europeMinsk());
        addTimeZoneInfo(timeZoneConstants.europeMonaco());
        addTimeZoneInfo(timeZoneConstants.europeMoscow());
        addTimeZoneInfo(timeZoneConstants.europeOslo());
        addTimeZoneInfo(timeZoneConstants.europeParis());
        addTimeZoneInfo(timeZoneConstants.europePodgorica());
        addTimeZoneInfo(timeZoneConstants.europePrague());
        addTimeZoneInfo(timeZoneConstants.europeRiga());
        addTimeZoneInfo(timeZoneConstants.europeRome());
        addTimeZoneInfo(timeZoneConstants.europeSamara());
        addTimeZoneInfo(timeZoneConstants.europeSarajevo());
        addTimeZoneInfo(timeZoneConstants.europeSimferopol());
        addTimeZoneInfo(timeZoneConstants.europeSkopje());
        addTimeZoneInfo(timeZoneConstants.europeSofia());
        addTimeZoneInfo(timeZoneConstants.europeStockholm());
        addTimeZoneInfo(timeZoneConstants.europeTallinn());
        addTimeZoneInfo(timeZoneConstants.europeTirane());
        addTimeZoneInfo(timeZoneConstants.europeUzhgorod());
        addTimeZoneInfo(timeZoneConstants.europeVaduz());
        addTimeZoneInfo(timeZoneConstants.europeVienna());
        addTimeZoneInfo(timeZoneConstants.europeVilnius());
        addTimeZoneInfo(timeZoneConstants.europeVolgograd());
        addTimeZoneInfo(timeZoneConstants.europeWarsaw());
        addTimeZoneInfo(timeZoneConstants.europeZagreb());
        addTimeZoneInfo(timeZoneConstants.europeZaporozhye());
        addTimeZoneInfo(timeZoneConstants.europeZurich());
        addTimeZoneInfo(timeZoneConstants.indianAntananarivo());
        addTimeZoneInfo(timeZoneConstants.indianChagos());
        addTimeZoneInfo(timeZoneConstants.indianChristmas());
        addTimeZoneInfo(timeZoneConstants.indianCocos());
        addTimeZoneInfo(timeZoneConstants.indianComoro());
        addTimeZoneInfo(timeZoneConstants.indianKerguelen());
        addTimeZoneInfo(timeZoneConstants.indianMahe());
        addTimeZoneInfo(timeZoneConstants.indianMaldives());
        addTimeZoneInfo(timeZoneConstants.indianMauritius());
        addTimeZoneInfo(timeZoneConstants.indianMayotte());
        addTimeZoneInfo(timeZoneConstants.indianReunion());
        addTimeZoneInfo(timeZoneConstants.pacificApia());
        addTimeZoneInfo(timeZoneConstants.pacificAuckland());
        addTimeZoneInfo(timeZoneConstants.pacificChatham());
        addTimeZoneInfo(timeZoneConstants.pacificEaster());
        addTimeZoneInfo(timeZoneConstants.pacificEfate());
        addTimeZoneInfo(timeZoneConstants.pacificEnderbury());
        addTimeZoneInfo(timeZoneConstants.pacificFakaofo());
        addTimeZoneInfo(timeZoneConstants.pacificFiji());
        addTimeZoneInfo(timeZoneConstants.pacificFunafuti());
        addTimeZoneInfo(timeZoneConstants.pacificGalapagos());
        addTimeZoneInfo(timeZoneConstants.pacificGambier());
        addTimeZoneInfo(timeZoneConstants.pacificGuadalcanal());
        addTimeZoneInfo(timeZoneConstants.pacificGuam());
        addTimeZoneInfo(timeZoneConstants.pacificHonolulu());
        addTimeZoneInfo(timeZoneConstants.pacificJohnston());
        addTimeZoneInfo(timeZoneConstants.pacificKiritimati());
        addTimeZoneInfo(timeZoneConstants.pacificKosrae());
        addTimeZoneInfo(timeZoneConstants.pacificKwajalein());
        addTimeZoneInfo(timeZoneConstants.pacificMajuro());
        addTimeZoneInfo(timeZoneConstants.pacificMarquesas());
        addTimeZoneInfo(timeZoneConstants.pacificMidway());
        addTimeZoneInfo(timeZoneConstants.pacificNauru());
        addTimeZoneInfo(timeZoneConstants.pacificNiue());
        addTimeZoneInfo(timeZoneConstants.pacificNorfolk());
        addTimeZoneInfo(timeZoneConstants.pacificNoumea());
        addTimeZoneInfo(timeZoneConstants.pacificPagoPago());
        addTimeZoneInfo(timeZoneConstants.pacificPalau());
        addTimeZoneInfo(timeZoneConstants.pacificPitcairn());
        addTimeZoneInfo(timeZoneConstants.pacificPonape());
        addTimeZoneInfo(timeZoneConstants.pacificPortMoresby());
        addTimeZoneInfo(timeZoneConstants.pacificRarotonga());
        addTimeZoneInfo(timeZoneConstants.pacificSaipan());
        addTimeZoneInfo(timeZoneConstants.pacificTahiti());
        addTimeZoneInfo(timeZoneConstants.pacificTarawa());
        addTimeZoneInfo(timeZoneConstants.pacificTongatapu());
        addTimeZoneInfo(timeZoneConstants.pacificTruk());
        addTimeZoneInfo(timeZoneConstants.pacificWake());
        addTimeZoneInfo(timeZoneConstants.pacificWallis());
        addTimeZoneInfo(timeZoneConstants.americaArgentinaSalta());
        addTimeZoneInfo(timeZoneConstants.americaArgentinaSanLuis());
        addTimeZoneInfo(timeZoneConstants.americaBahiaBanderas());
        addTimeZoneInfo(timeZoneConstants.americaIndianaTellCity());
        addTimeZoneInfo(timeZoneConstants.americaIndianaWinamac());
        addTimeZoneInfo(timeZoneConstants.americaMarigot());
        addTimeZoneInfo(timeZoneConstants.americaMatamoros());
        addTimeZoneInfo(timeZoneConstants.americaMetlakatla());
        addTimeZoneInfo(timeZoneConstants.americaNorthDakotaBeulah());
        addTimeZoneInfo(timeZoneConstants.americaOjinaga());
        addTimeZoneInfo(timeZoneConstants.americaResolute());
        addTimeZoneInfo(timeZoneConstants.americaSantaIsabel());
        addTimeZoneInfo(timeZoneConstants.americaSantarem());
        addTimeZoneInfo(timeZoneConstants.americaSitka());
        addTimeZoneInfo(timeZoneConstants.americaStBarthelemy());
        addTimeZoneInfo(timeZoneConstants.antarcticaMacquarie());
        addTimeZoneInfo(timeZoneConstants.arcticLongyearbyen());
        addTimeZoneInfo(timeZoneConstants.asiaNovokuznetsk());
        addTimeZoneInfo(timeZoneConstants.europeGuernsey());
        addTimeZoneInfo(timeZoneConstants.europeIsleofMan());
        addTimeZoneInfo(timeZoneConstants.europeJersey());
        addTimeZoneInfo(timeZoneConstants.europeMariehamn());
        addTimeZoneInfo(timeZoneConstants.europeSanMarino());
        addTimeZoneInfo(timeZoneConstants.europeVatican());
        addTimeZoneInfo(timeZoneConstants.africaAsmara());
        addTimeZoneInfo(timeZoneConstants.africaJuba());
        addTimeZoneInfo(timeZoneConstants.americaAtikokan());
        addTimeZoneInfo(timeZoneConstants.americaCreston());
        addTimeZoneInfo(timeZoneConstants.americaKralendijk());
        addTimeZoneInfo(timeZoneConstants.americaLowerPrinces());
        addTimeZoneInfo(timeZoneConstants.antarcticaSouthPole());
        addTimeZoneInfo(timeZoneConstants.asiaHebron());
        addTimeZoneInfo(timeZoneConstants.asiaHoChiMinh());
        addTimeZoneInfo(timeZoneConstants.asiaKathmandu());
        addTimeZoneInfo(timeZoneConstants.asiaKolkata());
        addTimeZoneInfo(timeZoneConstants.atlanticFaroe());
        addTimeZoneInfo(timeZoneConstants.utc());
    }
}
