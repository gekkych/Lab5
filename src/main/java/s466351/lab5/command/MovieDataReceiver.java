package s466351.lab5.command;

import s466351.lab5.movie.MovieData;

public interface MovieDataReceiver {
    String execute(String argument, MovieData data);
}