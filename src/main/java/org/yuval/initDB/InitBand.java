package org.yuval.initDB;

import org.yuval.dao.BandDao;
import org.yuval.dao.Crud;
import org.yuval.objects.Band;

import java.util.ArrayList;

import static org.yuval.utils.Parameters.IMAGE_FOLDER;

/**
 * Created by Yuval on 13-Mar-17.
 * this class is for initialize the band collection
 */
public class InitBand {

    //this is an insert of default bands
    public static void main(String[]args){
        ArrayList<Band>bands=new ArrayList<>();

        bands.add(new Band(1,"Metallica","Metallica is an American heavy metal band based in San Rafael," +
                " California. The band was formed in 1981 in Los Angeles when vocalist/guitarist James Hetfield responded" +
                " to an advertisement posted by drummer Lars Ulrich in a local newspaper. Metallica's current line-up comprises" +
                " founding members Hetfield and Ulrich, longtime lead guitarist Kirk Hammett and bassist Robert Trujillo" +
                ". Guitarist Dave Mustaine and bassists Ron McGovney, Cliff Burton and Jason Newsted are former members of the band.",
                IMAGE_FOLDER+"Metallica.png"));

        bands.add(new Band(2,"Aerosmith","Aerosmith is an American rock band, sometimes referred to as \"the Bad Boys fr" +
                "om Boston\"[4] and \"America's Greatest Rock and Roll Band\".[5][6][7][8] Their " +
                "style, which is rooted in blues-based hard rock,[9][10] has come to also incorporate elem" +
                "ents of pop,[11] heavy metal,[9] and rhythm and blues,[12] and has inspired many subsequent rock artist" +
                "s.[13] They were formed in Boston, Massachusetts in 1970.[14] Guitarist Joe Perry and bassist Tom Hamilton" +
                ", originally in a band together called the Jam Band, met up with vocalist/pianist/harmonicist Steven Tyler" +
                ", drummer Joey Kramer, and guitarist Ray Tabano, and formed Aerosmith. In 1971, Tabano was replaced by Brad Whit" +
                "ford, and the band began developing a following in Boston.",IMAGE_FOLDER+"Aerosmith.jpg"));

        bands.add(new Band(3,"Led Zeppelin","Led Zeppelin were an English rock band formed" +
                " in London in 1968. The group consisted of Robert Plant (Vocal), Jimmy Page (Guitar), John Paul Jones (Bass, Keyboard)" +
                " and John Bonham (Drums). The band's heavy, guitar-driven sound, rooted in blues and psychedelia on their early albums, has earned" +
                " them recognition as one of the progenitors of heavy metal. They achieved significant commercial success with albums such as Led Zeppelin (1969), " +
                "Led Zeppelin II (1969), Led Zeppelin III (1970), Led Zeppelin IV (1971), Houses of the Holy (1973), and Physical Graffiti (1975)." +
                " Their song ‘Stairway to Heaven’ is among the most popular and influential rock music of all time.",IMAGE_FOLDER+"led_zeppelin.jpg"));



        bands.add(new Band(4,"Queen","For the record, Freddie Mercury is one of the greatest talents that" +
                " rock n roll has ever seen. His vocal range and lyrics are among the finest you will find and his band’s prolific" +
                " rock power ballad “Bohemian Rhapsody” which appeared on the 1975 album, “A Night At The Opera,” is one of rock’s most" +
                " amazing songs. Queens performance at the 1985 Live Aid concert in Wembley Stadium in London, England has been ranked among the greatest" +
                " in rock history but sadly just 6 years later, Freddie Mercury died from complications due to AIDS.",IMAGE_FOLDER+"queen.jpg"));

        bands.add(new Band(5,"The Doors","While not on everyone’s list as a top 20 band, The Doors still get the nod because" +
                " of their ground-breaking live performances and also for having one of the greatest frontmen in rock history. Jim Morrison and his" +
                " band released eight albums between 1967 and 1971 and all but one landed in the Top 10. The Doors were huge in the US, selling three million" +
                " singles with their hits “Light My Fire”," +
                " “Hello, I Love You” and “Touch Me”. Jim Morrison died in France in 1971 at the age of 27.",IMAGE_FOLDER+"the_doors.jpg"));

        bands.add(new Band(6,"Black Sabbath","Now this is rock n roll at its finest. This powerhouse was formed in Birmingham" +
                " England in 1968, by guitarist/songwriter Tony Iommi, Geezer Butler, Ozzy Osbourne and Bill Ward. While the band was originally" +
                " a blues band, they soon turned their lyrics towards darker material and found a loyal audience who loved Osbournes dark songs. Osbourne" +
                " went hard into drug use and the band broke up in 1979" +
                " and only reunited shortly in 2013. They are proudly in the rock n roll Hall of Fame.",IMAGE_FOLDER+"BlackSabbath.jpg"));

        bands.add(new Band(7,"ACDC","Australia’s finest rock export, ACDC has entertained crowds for 40 years" +
                " despite some serious line-up changes. Malcolm and Angus Young formed the band in 1973 and their singer Bon Scott" +
                " died in 1980 after a night of hard partying. Brian Johnson joined the band as their new singer and had a great run until he" +
                " was forced to retire in 2015 due to ear issues and soon Guns N Roses frontman Axl Rose took over the duties. " +
                "The band has sold over 200 million records worldwide and is in the rock n roll hall of fame.",IMAGE_FOLDER+"acdc.jpg"));

        bands.add(new Band(8,"Nirvana","While this band doesnt seem old, its been over 20 years since they burst onto " +
                "the Seattle music scene and single handedly ushered in the grunge/punk movement while at the same time slamming the door " +
                "shut on the 80’s glam rock hair bands that were puking their bad music and hair all over MTV. This was the face of American youth" +
                " who were sick of bad music that cherry coated reality. They were young, pissed and ready to tear down the walls. Kids everywhere loved" +
                " their sound and the band ascended to the highest levels any punk influenced band in history. Even though" +
                " Cobain ended his life, his music plays on and his aura still has us captivated.",IMAGE_FOLDER+"Nirvana.jpg"));

        bands.add(new Band(9,"Neil Young and Crazy Horse","Crazy Horse is the incredible band that is best known by" +
                " playing on over 11 albums with Neil Young. They have earned the title as one of the best lives bands a fan could ever see. They first teamed up together in " +
                "1969 and have played on and off for the past 45 years and are still touring with Young as recently as 2015.",IMAGE_FOLDER+"Neil_Young.jpg"));

        bands.add(new Band(10,"Guns N Roses","GNR. No band hit faster or gained listeners quicker that Guns N Roses when their debut" +
                " masterpiece Appetite For Destruction hit music stores in 1987. Their sound, their songs and their look screamed strung out Hollywood" +
                " metal but their actual talent is what gained them fans fast and furiously as MTV quickly adopted the volatile band. While Guns would never" +
                " have another album that would reach the level of Appetite," +
                " they enjoyed a decade long run where they were the kings of rock n roll.",IMAGE_FOLDER+"guns_N_roses.jpg"));


        bands.add(new Band(11,"Pink Floyd","Pink Floyd has done more for drugs and music than perhaps any other band in rock" +
                " besides the Grateful Dead. With albums, laser shows and the all-time mind bending journey knowns as the movie called “The Wall,”" +
                " Pink Floyd built huge sonic walls and filled the clean silence with dripping tones  and lush mindscapes that kept listeners and viewers" +
                " lined up for the past 45 years. The band was originally Roger Waters (bass), Nick Mason (drums), Rick Wright (piano), and Syd Barrett (vocals)" +
                " but the line up changed when Syd died and later when Roger Waters took over" +
                " the band name and went on the road with a new group of musicians in the 2000’s.",IMAGE_FOLDER+"pink_floyd.jpg"));

        bands.add(new Band(12,"The Rolling Stones","While the Beatles may claim the number one spot, I still feel " +
                "that the Rolling Stones are the world’s favorite band. With a 50 year history of hits, Mick Jagger and Keith Richards " +
                "continue to kick out amazing live shows and world tours well into the 70’s. The Stones loved American rock and blues and " +
                "their careers have always followed that musical taste. If you listen to the 1968 album “Beggars Banquet”" +
                ", you will hear the sounds of soul and blues on almost every track.",IMAGE_FOLDER+"The_Rolling_Stones.jpg"));

        bands.add(new Band(13,"The Beatles","Of course, the number one band is the Beatles! This band shaped the sound of" +
                " music for decades and has sold more than 178 million albums in the U.S. alone. In the Beatles first recording sessions in ’62," +
                " they recorded “Love Me Do” and “Please Please Me,” which became the band’s first #1 single. They arrived in the US in 1964 and soon" +
                " drew the largest TV audience to ever watch a single show when they appeared on the Ed Sullivan show. From 1964-1969 the band released" +
                " six hit albums but announced their breakup in 1970. All the members of the band went on to their own fame but George Harrison died of cancer" +
                " in 2001 and John Lennon was killed in 1980 when a crazed fan shot him down in NYC.",IMAGE_FOLDER+"the_beatles.jpg"));




        Crud crud = new BandDao();
        crud.dropAll();
        bands.parallelStream()
                .forEach(band -> crud.create(band));
    }

}
