package nimrodpasha.cinema.initDB;

import nimrodpasha.cinema.dao.BandDao;
import nimrodpasha.cinema.dao.Crud;
import nimrodpasha.cinema.dao.ShowDao;
import nimrodpasha.cinema.objects.Show;
import nimrodpasha.cinema.utils.FillShowInstanceArrayInterface;
import nimrodpasha.cinema.utils.Helpers;
import nimrodpasha.cinema.dao.RandomId;

import java.util.ArrayList;

import static nimrodpasha.cinema.utils.Parameters.IMAGE_FOLDER;

/**
 * Created by Yuval on 14-Mar-17.
 * this class is for initialize the show collection
 */
public class InitShow {
    //this is an insert of default show
    public static void main(String[] args) {

        ArrayList<Show> shows = new ArrayList<>();

        RandomId randomId = new BandDao();
        FillShowInstanceArrayInterface fillShowInstanceArrayInterface = new Helpers();
        shows.add(new Show(1, "Great rock show", "The party will roll all night long during the North American " +
                "WorldWired tour as we’re psyched to announce that award-winning DJ and producer Mix " +
                "Master Mike will be spinning some of your favorite (and ours!) hard rock and metal tracks " +
                "throughout the evening at all of the stadium dates this summer. A pioneer in the hip-hop and" +
                " DJ communities, you’ll be able to catch his skillful, hard-hitting scratch work once the doors " +
                "open each night and between acts before we hit the stage.",
                randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray(),IMAGE_FOLDER+"rock_show_1.jpg"));

        shows.add(new Show(2, "Best show ever", "From the late 1960s to the early 1970s, many rock and roll " +
                "performers from the 1950s experienced major career revivals due to a temporary upswing of interest in their form of " +
                "music. The Revival was marked by a series of major concerts in the United States, and also spread to Europe where events " +
                "such as the Wembley concert attracted thousands of fans who came out to see the performers behind the" +
                " music.", randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray(),IMAGE_FOLDER+"rock_show_2.jpg"));

        shows.add(new Show(3,"Groovy show","Duran Duran has seldom played in the Buffalo/Niagara region. It was a no-brainer when this show was announced." +
                " The band did not let the fans down as they played not only the hits but also songs from their most recent effort Paper Gods. The " +
                "show ended with “Rio”. Afterwards fans screamed out “Reflex” an"+
        "d after a quick group huddle the band obliged and delivered a powerful performance of the song.", randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray()
                ,IMAGE_FOLDER+"rock_show_3.jpg"));

                shows.add(new Show(4,"Woodstock","This lineup of the band has been together since 2012. This version includes " +
                        "Gregg Rolie (Journey, Santana), guitarists Steve Lukather (Toto), Todd Rundgren (solo, Utopia, The New Cars)," +
                        " bassist Richard -Page (Mr. Mister), drummer Gregg Bissonette and multi –instrumentalist Warren Ham (Olivia Newton-John). " +
                        "The band played a great selection of songs from Ringo’s career, solo and " +
                        "Beatles as well as songs from the groups they are involved with."
                        , randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray(),IMAGE_FOLDER+"rock_show_4.jpg"));

        shows.add(new Show(5,"Europe tour","Marillion played their first US tour since 2012. The show featured opener John Wesley " +
                "who single-handedly put on a great show of his own. Marillion featured a few tracks from their critically acclaimed " +
                "recent release F.E.A.R. The set consisted of all H (Hogarth) era songs that was until the band returned with “Sugar " +
                "Mice” for the encore. Among the highlights were “Afraid Of Sunlight” and " +
                "“Easter”both of which received the loudest ovations of the night.",
                randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray(),IMAGE_FOLDER+"rock_show_5.jpg"));

        shows.add(new Show(6,"Africa tour","The recently inducted member to the Rock and Roll Hall of Fame is never at a loss for words." +
                " Miller tore the board from the Hall to shreds over the way the whole process was handled. It got so bad they cut off his press " +
                "conference. Miller delivered a set that showed why he belongs in the Hall. Miller delivered the goods with “Jungle Love”, “Take The Money " +
                "and Run” and “Abracadabra” and that was just for starters. Mixing in rare " +
                "treats ending with “The Stake” and “Jet Airliner” were icing on the cake.",
                randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray(),IMAGE_FOLDER+"rock_show_6.jpg"));

        shows.add(new Show(7,"Middle east tour","John Fogerty has been around a very long time. During the earlier years of" +
                " his solo career, Fogerty refused to play CCR material. Luckily that all changed now he plays most of those beloved songs" +
                " and sprinkles in a few solo songs. Seeing Fogerty perform at age 70, was a treat. It was quite surprising to see him perform with " +
                "such zest this late into his career. It appears the years he spent inactive have served him well." +
                " Do not miss this guy if he comes to your area.",
                randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray(),IMAGE_FOLDER+"rock_show_7.jpg"));

        shows.add(new Show(8,"Big in japan","Olivia has yet to ever disappoint and this was no exception. She gives everyone a " +
                "taste of all areas of her career from Grease to the 80’s and even the early 70’s. Seeing her perform such legendary songs as" +
                " “Please Mr. Please” and “Have You Never Been Mellow” was worth the price of admission alone.",
                randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray(),IMAGE_FOLDER+"rock_show_8.jpg"));

        shows.add(new Show(9,"Styx","Having seen Styx numerous times over recent years and never once seeing the" +
                " same show twice is just one reason of what makes this band so special. The set list changes every tour," +
                " sometimes on a show to show basis. Finally seeing Gowan perform “A Criminal Mind” as well as a rare play of " +
                "“Lights” from Cornerstone were the icing on the cake.",
                randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray(),IMAGE_FOLDER+"rock_show_9.jpg"));

        shows.add(new Show(10,"Rock n' Roll","One of the most anticipated tours of the fall landed in town in early" +
                " November to the delight of many progressive rock fans. One-time Yes members Jon Anderson, Trevor Rabin" +
                " and Rick Wakeman put on a show that not only focused on the classic hits but also treated the fans with" +
                " gems that haven’t been played in years such as “Changes” and “Hold On”. Overall," +
                " an extremely enjoyable evening for everyone in attendance.",
                randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray(),IMAGE_FOLDER+"rock_show_10.jpg"));

        shows.add(new Show(11,"Another one bite the dust","The Fallsview Casino welcomed the voice of Starship Mickey Thomas. There really isn’t " +
                "anyone out there today performing the music of Jefferson Starship and Starship with the authenticity that Thomas brings to the table. " +
                "Hearing such classics from “Jane” to “Find Your Way Back” alongside the Starship number one smashes of “We Built This City” and " +
                "“Nothing’s Gonna Stop Us Now” was nothing less than extraordinary.",
                randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray(),IMAGE_FOLDER+"rock_show_11.jpg"));

        shows.add(new Show(12,"Rock Hard","Everything about this night was spot on in regards to the weather, atmosphere and ambiance. While singer David Coverdale" +
                " may not be able to sing quite as he once did (he has had vocal chord issues in the past)  that was irrelevant. This show was about a great lineup of" +
                " musicians playing one of the best catalogs in the history of rock and roll. Having last played the Buffalo market in 1990 we can finally say the " +
                "drought was over. “Sailing Ships” and “Judgement Day” were thrown in to reward the long time fans who have stood by the band through thick and thin." +
                " Overall it was a night filled with" +
                " great songs and one that took many back to remember the glory days of their past.",
                randomId.randomId(), fillShowInstanceArrayInterface.fillShowInstanceArray(),IMAGE_FOLDER+"rock_show_12.jpg"));

        Crud crud = new ShowDao();
        crud.dropAll();

        shows.parallelStream()
                .forEach(show -> crud.create(show));

    }
}
