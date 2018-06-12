package model;

import java.util.Objects;

public class SongRequest {
    private String singerName;
    private Song song;

    public SongRequest(String singerName, Song song) {
        this.singerName = singerName;
        this.song = song;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongRequest that = (SongRequest) o;
        return Objects.equals(singerName, that.singerName) &&
                Objects.equals(song, that.song);
    }

    @Override
    public int hashCode() {

        return Objects.hash(singerName, song);
    }

    @Override
    public String toString() {
        return "SongRequest{" +
                "singerName='" + singerName + '\'' +
                ", song='" + song + '\'' +
                '}';
    }
}
