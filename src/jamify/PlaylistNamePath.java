
package jamify;

import java.io.Serializable;

public class  PlaylistNamePath implements Serializable {

    private String playListName;
    private String playListPath;

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public String getPlayListPath() {
        return playListPath;
    }

    public void setPlayListPath(String playListPath) {
        this.playListPath = playListPath;
    }


}
