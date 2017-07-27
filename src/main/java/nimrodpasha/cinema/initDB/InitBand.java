//package nimrodpasha.cinema.initDB;
//
//import nimrodpasha.cinema.dao.BandDao;
//import nimrodpasha.cinema.dao.Crud;
//import nimrodpasha.cinema.objects.Band;
//
//import java.util.ArrayList;
//
//import static nimrodpasha.cinema.utils.Parameters.DURATION;
//import static nimrodpasha.cinema.utils.Parameters.IMAGE_FOLDER;
//
///**
// * Created by Yuval on 13-Mar-17.
// * this class is for initialize the band collection
// */
//public class InitBand {
//
//    //this is an insert of default bands
//    public static void main(String[]args){
//        ArrayList<Band>bands=new ArrayList<>();
//
//        bands.add(new Band(1,"Fast and Furious 8","After Dom is recruited by a mysterious villainess hacker," +
//                " he turns against his family and most importantly," +
//                " goes on the wrong side of the law again." +
//                " Now, the crew must unite to bring home the man who made them a family and stop her from unleashing chaos on a grand scale.",
//                IMAGE_FOLDER+"fast8.jpg","F. Gary Gray","Vin Diesel, Jason Statham, Dwayne Johnson, Michelle Rodriguez, Tyrese Gibson","2017","Action, Thriller, Crime","136 min"));
//
//
//
//
//        bands.add(new Band(2,"McLaren","The story of Bruce McLaren, the New Zealander who founded the McLaren Motor Racing team. " +
//                "A man who showed the world that a man of humble beginnings could take on the elite of motor racing and win." +
//                " Now, the crew must unite to bring home the man who made them a family and stop her from unleashing chaos on a grand scale.",
//                IMAGE_FOLDER+"Maclaren.jpg","Roger Donaldson"," Mario Andretti, Alastair Caldwell, Dwayne Cameron","2017","Documentary, Action,  Drama","92 min"));
//
//
//
//        bands.add(new Band(3,"Pink Panther","The Pink Panther diamond is stolen once again from Lugash and the authorities call in Chief Inspector Clouseau from France." +
//                " His plane disappears en-route. This time, famous French TV reporter Marie Jouvet sets out to solve the mystery and starts to interview everybody connected to Clouseau. " +
//                "Each interviewee Dreyfus, Sir Charles & Lady Lytton (an ex-wife of Clouseau), George Lytton, Hercule Lajoy (assistant in A Shot In The Dark), " +
//                "and Cato tell of their run-ins with Clouseau. She is also kidnapped by mobster Bruno Langlois who doesn't want Clouseau found but she continues and finds Clouseau Sr.," +
//                " Clouseau's father. Is Clouseau alive or is he dead? Each interview has not-yet-seen or famous clips from the previous movies (since Peter Sellers has sadly passed away) " +
//                "as Marie continues to get a honest view or impression of the great French detective",
//                IMAGE_FOLDER+"PimkPanter.jpg", "Blake Edwards","Julie Andrews ,Denise Crosby,Claudia Cardinale ","1982", "Comedy, Crime, Mystery","62 min"));
//
//
//
//        bands.add(new Band(4,"Kung Fu Panda 3","When Po's long-lost panda father suddenly reappears, the reunited duo travels to a secret panda paradise to meet scores of hilarious new panda characters." +
//                " But when the supernatural villain Kai begins to sweep across China defeating all the kung fu masters, Po must do the impossible-learn to train a village full of his fun-loving, " +
//                "clumsy brethren to become the ultimate band of Kung Fu Pandas",
//                IMAGE_FOLDER+"Panda.jpg","Jennifer Yuh","Angelina Jolie as Tigress,Seth Rogen as Mantis,Bryan Cranston as Li,Kate Hudson as Mei Mei","2017","Action,Animation,Comedy,Family,Fantasy","88 min"));
//
//
//
//        bands.add(new Band(5,"The LEGO Batman Movie","There are big changes brewing in Gotham City, and if he wants to save the city from The Joker's hostile takeover," +
//                " Batman may have to drop the lone vigilante thing, try to work with others and maybe," +
//                " just maybe, learn to lighten up.",
//                IMAGE_FOLDER+"Lego.jpg",
//
//                "Chris McKay","Rosario Dawson as Batgirl,Channing Tatum as Superman,Ralph Fiennes as Alfred Pennyworth,Zoë Kravitz as Catwoman","2017","Action,Animation,Family,Fantasy","76 min"));
//
//
//        bands.add(new Band(6,"Kong: Skull Island","A washed up monster chaser convinces the U.S. Government to fund a trip to an unexplored island in the South Pacific. " +
//                "Under the guise of geological research, the team travels to Skull Island. Upon arrival, the group discover that their mission may be complicated by the wildlife which inhabits the island." +
//                " The beautiful vistas and deadly creatures create a visually stunning experience that is sure to keep your attention." +
//                " just maybe, learn to lighten up.",
//                IMAGE_FOLDER+"KONG.jpg","Jordan Vogt-Roberts","Brie Larson as Mason Weaver,Tom Hiddleston as James Conrad,Samuel L. Jackson as Preston Packard","2017","Action,Adventure,Fantasy,Sci-Fi","102 min"));
//
//
//        bands.add(new Band(7,"CHIPS","Jon Baker (Shepard) and Frank Ponch Poncherello (Peña) have just joined the California Highway Patrol (CHP) in Los Angeles, but for very different reasons." +
//                " Baker is a beaten-up former pro motorbiker trying to put his life and marriage back together. Poncherello is a cocky undercover Federal agent investigating a multi-million dollar heist that may be an inside-job inside the CHP." +
//                " The inexperienced rookie and the hardened pro are teamed together, but clash more than click, so kick-starting a real partnership is easier said than done. " +
//                "But with Baker's unique bike skills and Ponch's street savvy it might just work...if they don't drive each other crazy first.",
//                IMAGE_FOLDER+"CHIPS.jpg","Dax Shepard","Kristen Bell as Karen,Rosa Salazar as Ava,Vincent D'Onofrio as Vic Brown","2017","Action,Comedy,Crime","84 min"));
//
//
//        bands.add(new Band(8,"The Mask","Stanley Ipkiss (Jim Carrey) is a bank clerk that is an incredibly nice man. Unfortunately," +
//                " he is too nice for his own good and is a pushover when it comes to confrontations. After one of the worst days of his life, he finds a mask that depicts Loki, " +
//                "the Norse night god of mischief. Now, when he puts it on, he becomes his inner, self: a cartoon romantic wild man. However, a small time crime boss, Dorian Tyrel (Peter Greene), " +
//                "comes across this character dubbed The Mask by the media. After Ipkiss's alter ego indirectly kills his friend in crime," +
//                " Tyrel now wants this green-faced goon destroyed.",
//                IMAGE_FOLDER+"MASK.jpg","Chuck Russell","Jim Carrey as Stanley Ipkiss/The Mask,Cameron Diaz as Tina Carlyle,Amy Yasbeck as Peggy Brandt,Joely Fisher as Maggie","1994","Action,Comedy,CrimeAction,Family,Fantasy","88 min"));
//
//
//
//
////
////        bands.add(new Band(1,"Metallica","Metallica is an American heavy metal band based in San Rafael," +
////                " California. The band was formed in 1981 in Los Angeles when vocalist/guitarist James Hetfield responded" +
////                " to an advertisement posted by drummer Lars Ulrich in a local newspaper. Metallica's current line-up comprises" +
////                " founding members Hetfield and Ulrich, longtime lead guitarist Kirk Hammett and bassist Robert Trujillo" +
////                ". Guitarist Dave Mustaine and bassists Ron McGovney, Cliff Burton and Jason Newsted are former members of the band.",
////                IMAGE_FOLDER+"Metallica.png"));
////
////        bands.add(new Band(2,"Aerosmith","Aerosmith is an American rock band, sometimes referred to as \"the Bad Boys fr" +
////                "om Boston\"[4] and \"America's Greatest Rock and Roll Band\".[5][6][7][8] Their " +
////                "style, which is rooted in blues-based hard rock,[9][10] has come to also incorporate elem" +
////                "ents of pop,[11] heavy metal,[9] and rhythm and blues,[12] and has inspired many subsequent rock artist" +
////                "s.[13] They were formed in Boston, Massachusetts in 1970.[14] Guitarist Joe Perry and bassist Tom Hamilton" +
////                ", originally in a band together called the Jam Band, met up with vocalist/pianist/harmonicist Steven Tyler" +
////                ", drummer Joey Kramer, and guitarist Ray Tabano, and formed Aerosmith. In 1971, Tabano was replaced by Brad Whit" +
////                "ford, and the band began developing a following in Boston.",IMAGE_FOLDER+"Aerosmith.jpg"));
////
////        bands.add(new Band(3,"Led Zeppelin","Led Zeppelin were an English rock band formed" +
////                " in London in 1968. The group consisted of Robert Plant (Vocal), Jimmy Page (Guitar), John Paul Jones (Bass, Keyboard)" +
////                " and John Bonham (Drums). The band's heavy, guitar-driven sound, rooted in blues and psychedelia on their early albums, has earned" +
////                " them recognition as one of the progenitors of heavy metal. They achieved significant commercial success with albums such as Led Zeppelin (1969), " +
////                "Led Zeppelin II (1969), Led Zeppelin III (1970), Led Zeppelin IV (1971), Houses of the Holy (1973), and Physical Graffiti (1975)." +
////                " Their song ‘Stairway to Heaven’ is among the most popular and influential rock music of all time.",IMAGE_FOLDER+"led_zeppelin.jpg"));
////
////
////
////        bands.add(new Band(4,"Queen","For the record, Freddie Mercury is one of the greatest talents that" +
////                " rock n roll has ever seen. His vocal range and lyrics are among the finest you will find and his band’s prolific" +
////                " rock power ballad “Bohemian Rhapsody” which appeared on the 1975 album, “A Night At The Opera,” is one of rock’s most" +
////                " amazing songs. Queens performance at the 1985 Live Aid concert in Wembley Stadium in London, England has been ranked among the greatest" +
////                " in rock history but sadly just 6 years later, Freddie Mercury died from complications due to AIDS.",IMAGE_FOLDER+"queen.jpg"));
////
////        bands.add(new Band(5,"The Doors","While not on everyone’s list as a top 20 band, The Doors still get the nod because" +
////                " of their ground-breaking live performances and also for having one of the greatest frontmen in rock history. Jim Morrison and his" +
////                " band released eight albums between 1967 and 1971 and all but one landed in the Top 10. The Doors were huge in the US, selling three million" +
////                " singles with their hits “Light My Fire”," +
////                " “Hello, I Love You” and “Touch Me”. Jim Morrison died in France in 1971 at the age of 27.",IMAGE_FOLDER+"the_doors.jpg"));
////
////        bands.add(new Band(6,"Black Sabbath","Now this is rock n roll at its finest. This powerhouse was formed in Birmingham" +
////                " England in 1968, by guitarist/songwriter Tony Iommi, Geezer Butler, Ozzy Osbourne and Bill Ward. While the band was originally" +
////                " a blues band, they soon turned their lyrics towards darker material and found a loyal audience who loved Osbournes dark songs. Osbourne" +
////                " went hard into drug use and the band broke up in 1979" +
////                " and only reunited shortly in 2013. They are proudly in the rock n roll Hall of Fame.",IMAGE_FOLDER+"BlackSabbath.jpg"));
////
////        bands.add(new Band(7,"ACDC","Australia’s finest rock export, ACDC has entertained crowds for 40 years" +
////                " despite some serious line-up changes. Malcolm and Angus Young formed the band in 1973 and their singer Bon Scott" +
////                " died in 1980 after a night of hard partying. Brian Johnson joined the band as their new singer and had a great run until he" +
////                " was forced to retire in 2015 due to ear issues and soon Guns N Roses frontman Axl Rose took over the duties. " +
////                "The band has sold over 200 million records worldwide and is in the rock n roll hall of fame.",IMAGE_FOLDER+"acdc.jpg"));
////
////        bands.add(new Band(8,"Nirvana","While this band doesnt seem old, its been over 20 years since they burst onto " +
////                "the Seattle music scene and single handedly ushered in the grunge/punk movement while at the same time slamming the door " +
////                "shut on the 80’s glam rock hair bands that were puking their bad music and hair all over MTV. This was the face of American youth" +
////                " who were sick of bad music that cherry coated reality. They were young, pissed and ready to tear down the walls. Kids everywhere loved" +
////                " their sound and the band ascended to the highest levels any punk influenced band in history. Even though" +
////                " Cobain ended his life, his music plays on and his aura still has us captivated.",IMAGE_FOLDER+"Nirvana.jpg"));
////
////        bands.add(new Band(9,"Neil Young and Crazy Horse","Crazy Horse is the incredible band that is best known by" +
////                " playing on over 11 albums with Neil Young. They have earned the title as one of the best lives bands a fan could ever see. They first teamed up together in " +
////                "1969 and have played on and off for the past 45 years and are still touring with Young as recently as 2015.",IMAGE_FOLDER+"Neil_Young.jpg"));
////
////        bands.add(new Band(10,"Guns N Roses","GNR. No band hit faster or gained listeners quicker that Guns N Roses when their debut" +
////                " masterpiece Appetite For Destruction hit music stores in 1987. Their sound, their songs and their look screamed strung out Hollywood" +
////                " metal but their actual talent is what gained them fans fast and furiously as MTV quickly adopted the volatile band. While Guns would never" +
////                " have another album that would reach the level of Appetite," +
////                " they enjoyed a decade long run where they were the kings of rock n roll.",IMAGE_FOLDER+"guns_N_roses.jpg"));
////
////
////        bands.add(new Band(11,"Pink Floyd","Pink Floyd has done more for drugs and music than perhaps any other band in rock" +
////                " besides the Grateful Dead. With albums, laser shows and the all-time mind bending journey knowns as the movie called “The Wall,”" +
////                " Pink Floyd built huge sonic walls and filled the clean silence with dripping tones  and lush mindscapes that kept listeners and viewers" +
////                " lined up for the past 45 years. The band was originally Roger Waters (bass), Nick Mason (drums), Rick Wright (piano), and Syd Barrett (vocals)" +
////                " but the line up changed when Syd died and later when Roger Waters took over" +
////                " the band name and went on the road with a new group of musicians in the 2000’s.",IMAGE_FOLDER+"pink_floyd.jpg"));
////
////        bands.add(new Band(12,"The Rolling Stones","While the Beatles may claim the number one spot, I still feel " +
////                "that the Rolling Stones are the world’s favorite band. With a 50 year history of hits, Mick Jagger and Keith Richards " +
////                "continue to kick out amazing live shows and world tours well into the 70’s. The Stones loved American rock and blues and " +
////                "their careers have always followed that musical taste. If you listen to the 1968 album “Beggars Banquet”" +
////                ", you will hear the sounds of soul and blues on almost every track.",IMAGE_FOLDER+"The_Rolling_Stones.jpg"));
////
////        bands.add(new Band(13,"The Beatles","Of course, the number one band is the Beatles! This band shaped the sound of" +
////                " music for decades and has sold more than 178 million albums in the U.S. alone. In the Beatles first recording sessions in ’62," +
////                " they recorded “Love Me Do” and “Please Please Me,” which became the band’s first #1 single. They arrived in the US in 1964 and soon" +
////                " drew the largest TV audience to ever watch a single show when they appeared on the Ed Sullivan show. From 1964-1969 the band released" +
////                " six hit albums but announced their breakup in 1970. All the members of the band went on to their own fame but George Harrison died of cancer" +
////                " in 2001 and John Lennon was killed in 1980 when a crazed fan shot him down in NYC.",IMAGE_FOLDER+"the_beatles.jpg"));
//
//
//
//
//        Crud crud = new BandDao();
//        crud.dropAll();
//        bands.parallelStream()
//                .forEach(band -> crud.create(band));
//    }
//
//}
