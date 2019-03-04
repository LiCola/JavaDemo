package json;

/**
 * @author LiCola
 * @date 2019-03-04
 */
public class ListItemBean {

  /**
   * id : 262
   * user_id : 108
   * template_id : 5
   * shop_id : null
   * name : 1547892194
   * video_hash : 2adb651a0f2a45b7aa7fcab9c737b75a
   * video_url : http://video.youji.pro/2adb651a0f2a45b7aa7fcab9c737b75a/1ebde3912df94472b776783609ee6ae9-d2b891aa54fc808405074c60a18f4dca-ld.mp4
   * snapshot : http://video.youji.pro/2adb651a0f2a45b7aa7fcab9c737b75a/covers/6128bad6d7e147beb0f109a0d4b7c909-00001.jpg
   * video_cover : http://video.youji.pro/image/cover/6C8AA549609748BAA76AAB9F34EF83BC-6-2.png
   * play_number : 0
   * duration : 8.045
   * create_time : 1547892196
   * update_time : 1547892196
   */

  private int id;
  private int user_id;
  private int template_id;
  private Object shop_id;
  private String name;
  private String video_hash;
  private String video_url;
  private String snapshot;
  private String video_cover;
  private int play_number;
  private double duration;
  private int create_time;
  private int update_time;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public int getTemplate_id() {
    return template_id;
  }

  public void setTemplate_id(int template_id) {
    this.template_id = template_id;
  }

  public Object getShop_id() {
    return shop_id;
  }

  public void setShop_id(Object shop_id) {
    this.shop_id = shop_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVideo_hash() {
    return video_hash;
  }

  public void setVideo_hash(String video_hash) {
    this.video_hash = video_hash;
  }

  public String getVideo_url() {
    return video_url;
  }

  public void setVideo_url(String video_url) {
    this.video_url = video_url;
  }

  public String getSnapshot() {
    return snapshot;
  }

  public void setSnapshot(String snapshot) {
    this.snapshot = snapshot;
  }

  public String getVideo_cover() {
    return video_cover;
  }

  public void setVideo_cover(String video_cover) {
    this.video_cover = video_cover;
  }

  public int getPlay_number() {
    return play_number;
  }

  public void setPlay_number(int play_number) {
    this.play_number = play_number;
  }

  public double getDuration() {
    return duration;
  }

  public void setDuration(double duration) {
    this.duration = duration;
  }

  public int getCreate_time() {
    return create_time;
  }

  public void setCreate_time(int create_time) {
    this.create_time = create_time;
  }

  public int getUpdate_time() {
    return update_time;
  }

  public void setUpdate_time(int update_time) {
    this.update_time = update_time;
  }
}
