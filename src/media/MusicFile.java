package media;

import com.mpatric.mp3agic.Mp3File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public final class MusicFile implements Serializable
{
    private static final long serialVersionUID = -1176320510135364380L;
    private String trackName;
    private String artistName;
    private String albumInfo;
    private String genre;
    private byte[] musicFileExtract;       //do not use musicFileExtract for now, as it is always null.
    private String tag;


    public MusicFile(Mp3File song) {
        if (song.hasId3v1Tag()) {
            trackName = song.getId3v1Tag().getTitle();
            artistName = song.getId3v1Tag().getArtist();
            albumInfo = song.getId3v1Tag().getAlbum();
            genre = song.getId3v1Tag().getGenreDescription();
            song.removeId3v1Tag();
            tag = "Id3v1";
        }
        else if (song.hasId3v2Tag()) {
            trackName = song.getId3v2Tag().getTitle();
            artistName = song.getId3v2Tag().getArtist();
            albumInfo = song.getId3v2Tag().getAlbum();
            genre = song.getId3v2Tag().getGenreDescription();
            song.removeId3v2Tag();
            tag = "id3v2";
        }
        else {System.out.println ("Unknown tag");}
    }

    public void setTrackName(String trackName)
    {
        this.trackName = trackName;
    }

    public void setArtistName(String artistName)
    {
        this.artistName = artistName;
    }

    public void setAlbumInfo(String albumInfo)
    {
        this.albumInfo = albumInfo;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public void setMusicFileExtract(byte[] musicFileExtract)
    {
        this.musicFileExtract = musicFileExtract;
    }

    public String getTrackName()
    {
        return trackName;
    }

    public String getArtistName()
    {
        return artistName;
    }

    public String getAlbumInfo()
    {
        return albumInfo;
    }

    public String getGenre()
    {
        return genre;
    }

    public byte[] getMusicFileExtract()
    {
        return musicFileExtract;
    }

    @Override
    public String toString()
    {
        return "MusicFile{" +
                "trackName='" + trackName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", albumInfo='" + albumInfo + '\'' +
                ", genre='" + genre + '\'' +
               // ", number of bits: "+musicFileExtract.length +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicFile musicFile = (MusicFile) o;
        return trackName.equals(musicFile.trackName) &&
                artistName.equals(musicFile.artistName) &&
                albumInfo.equals(musicFile.albumInfo) &&
                genre.equals(musicFile.genre) &&
                Arrays.equals(musicFileExtract, musicFile.musicFileExtract);
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(trackName, artistName, albumInfo, genre);
        result = 31 * result + Arrays.hashCode(musicFileExtract);
        return result;
    }
}
