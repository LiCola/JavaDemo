package json;

import java.util.List;

/**
 * @author LiCola
 * @date 2019-03-04
 */
public class ConfigBean {

  /**
   * actions : [{"actionMaskGifName":"b7d8dad01b72c1e64c3ff5bf684e81b5.gif","aimImageName":"f0ae7e4f04a43b5027f2fe1061a405b0.png","duration":4,"startTime":0},{"actionMaskGifName":"fba827f5d48e6659748b4a7682c50a6e.gif","aimImageName":"b8566694ac172ae970dd78b9fc136047.png","duration":3,"startTime":4},{"actionMaskGifName":"a071575ea0c633c39838ba8cf8efa7d5.gif","aimImageName":"8a4f3814a2d456d619874500f790ea53.png","duration":3,"startTime":7}]
   * masks : []
   * pasters : [{"name":"默认贴纸","fileName":"49446e5f61508334d201007472a8c7b0.jpg","images":[{"startIndex":0,"startOffsetTime":0,"endIndex":3,"endOffsetTime":0,"isGif":true,"fileName":"914319100f6727c2fce124528fd7bfe2","indexStartTime":0}],"transitions":[],"effects":[{"name":"抖动","startIndex":0,"startOffsetTime":0,"endIndex":0,"endOffsetTime":2},{"name":"重影","startIndex":1,"startOffsetTime":0,"endIndex":1,"endOffsetTime":3}]}]
   * transitions : [{"name":"百叶窗","index":1,"duration":0.3}]
   * templateId : 2
   * templateName : 杭帮菜
   * duration : 10
   * templateCoverName : https://uboxs-img.oss-cn-hangzhou.aliyuncs.com/534f2292ff5f4d5c0dfda8c92bd80dd5.jpg
   * templateSampleVideoName : 856968c1db902fed2e3377e8bc48c99e.mp4
   * coverCaptureTime : 9
   * filter : 焦黄
   * musics : [{"name":"江南最忆","duration":10,"mixWeight":50,"fileName":"e0be53c02609c24f8a7b83fe9a83ef43.mp3","startTime":0,"cover":"1fb760891a50de904e5f7b880d3b41b1.jpg"}]
   * sections : [{"duration":4,"hint":"镜头向右环绕美食慢慢移动","startTime":0},{"duration":3,"hint":"镜头保持稳定不动","startTime":4},{"duration":3,"hint":"镜头保持稳定不动","startTime":7}]
   */

  private String templateId;
  private String templateName;
  private int duration;
  private String templateCoverName;
  private String templateSampleVideoName;
  private int coverCaptureTime;
  private String filter;
  private List<ActionsBean> actions;
  private List<?> masks;
  private List<PastersBean> pasters;
  private List<TransitionsBean> transitions;
  private List<MusicsBean> musics;
  private List<SectionsBean> sections;

  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getTemplateCoverName() {
    return templateCoverName;
  }

  public void setTemplateCoverName(String templateCoverName) {
    this.templateCoverName = templateCoverName;
  }

  public String getTemplateSampleVideoName() {
    return templateSampleVideoName;
  }

  public void setTemplateSampleVideoName(String templateSampleVideoName) {
    this.templateSampleVideoName = templateSampleVideoName;
  }

  public int getCoverCaptureTime() {
    return coverCaptureTime;
  }

  public void setCoverCaptureTime(int coverCaptureTime) {
    this.coverCaptureTime = coverCaptureTime;
  }

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  public List<ActionsBean> getActions() {
    return actions;
  }

  public void setActions(List<ActionsBean> actions) {
    this.actions = actions;
  }

  public List<?> getMasks() {
    return masks;
  }

  public void setMasks(List<?> masks) {
    this.masks = masks;
  }

  public List<PastersBean> getPasters() {
    return pasters;
  }

  public void setPasters(List<PastersBean> pasters) {
    this.pasters = pasters;
  }

  public List<TransitionsBean> getTransitions() {
    return transitions;
  }

  public void setTransitions(List<TransitionsBean> transitions) {
    this.transitions = transitions;
  }

  public List<MusicsBean> getMusics() {
    return musics;
  }

  public void setMusics(List<MusicsBean> musics) {
    this.musics = musics;
  }

  public List<SectionsBean> getSections() {
    return sections;
  }

  public void setSections(List<SectionsBean> sections) {
    this.sections = sections;
  }

  public static class ActionsBean {

    /**
     * actionMaskGifName : b7d8dad01b72c1e64c3ff5bf684e81b5.gif
     * aimImageName : f0ae7e4f04a43b5027f2fe1061a405b0.png
     * duration : 4
     * startTime : 0
     */

    private String actionMaskGifName;
    private String aimImageName;
    private int duration;
    private int startTime;

    public String getActionMaskGifName() {
      return actionMaskGifName;
    }

    public void setActionMaskGifName(String actionMaskGifName) {
      this.actionMaskGifName = actionMaskGifName;
    }

    public String getAimImageName() {
      return aimImageName;
    }

    public void setAimImageName(String aimImageName) {
      this.aimImageName = aimImageName;
    }

    public int getDuration() {
      return duration;
    }

    public void setDuration(int duration) {
      this.duration = duration;
    }

    public int getStartTime() {
      return startTime;
    }

    public void setStartTime(int startTime) {
      this.startTime = startTime;
    }
  }

  public static class PastersBean {

    /**
     * name : 默认贴纸
     * fileName : 49446e5f61508334d201007472a8c7b0.jpg
     * images : [{"startIndex":0,"startOffsetTime":0,"endIndex":3,"endOffsetTime":0,"isGif":true,"fileName":"914319100f6727c2fce124528fd7bfe2","indexStartTime":0}]
     * transitions : []
     * effects : [{"name":"抖动","startIndex":0,"startOffsetTime":0,"endIndex":0,"endOffsetTime":2},{"name":"重影","startIndex":1,"startOffsetTime":0,"endIndex":1,"endOffsetTime":3}]
     */

    private String name;
    private String fileName;
    private List<ImagesBean> images;
    private List<?> transitions;
    private List<EffectsBean> effects;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getFileName() {
      return fileName;
    }

    public void setFileName(String fileName) {
      this.fileName = fileName;
    }

    public List<ImagesBean> getImages() {
      return images;
    }

    public void setImages(List<ImagesBean> images) {
      this.images = images;
    }

    public List<?> getTransitions() {
      return transitions;
    }

    public void setTransitions(List<?> transitions) {
      this.transitions = transitions;
    }

    public List<EffectsBean> getEffects() {
      return effects;
    }

    public void setEffects(List<EffectsBean> effects) {
      this.effects = effects;
    }

    public static class ImagesBean {

      /**
       * startIndex : 0
       * startOffsetTime : 0
       * endIndex : 3
       * endOffsetTime : 0
       * isGif : true
       * fileName : 914319100f6727c2fce124528fd7bfe2
       * indexStartTime : 0
       */

      private int startIndex;
      private int startOffsetTime;
      private int endIndex;
      private int endOffsetTime;
      private boolean isGif;
      private String fileName;
      private int indexStartTime;

      public int getStartIndex() {
        return startIndex;
      }

      public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
      }

      public int getStartOffsetTime() {
        return startOffsetTime;
      }

      public void setStartOffsetTime(int startOffsetTime) {
        this.startOffsetTime = startOffsetTime;
      }

      public int getEndIndex() {
        return endIndex;
      }

      public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
      }

      public int getEndOffsetTime() {
        return endOffsetTime;
      }

      public void setEndOffsetTime(int endOffsetTime) {
        this.endOffsetTime = endOffsetTime;
      }

      public boolean isIsGif() {
        return isGif;
      }

      public void setIsGif(boolean isGif) {
        this.isGif = isGif;
      }

      public String getFileName() {
        return fileName;
      }

      public void setFileName(String fileName) {
        this.fileName = fileName;
      }

      public int getIndexStartTime() {
        return indexStartTime;
      }

      public void setIndexStartTime(int indexStartTime) {
        this.indexStartTime = indexStartTime;
      }
    }

    public static class EffectsBean {

      /**
       * name : 抖动
       * startIndex : 0
       * startOffsetTime : 0
       * endIndex : 0
       * endOffsetTime : 2
       */

      private String name;
      private int startIndex;
      private int startOffsetTime;
      private int endIndex;
      private int endOffsetTime;

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public int getStartIndex() {
        return startIndex;
      }

      public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
      }

      public int getStartOffsetTime() {
        return startOffsetTime;
      }

      public void setStartOffsetTime(int startOffsetTime) {
        this.startOffsetTime = startOffsetTime;
      }

      public int getEndIndex() {
        return endIndex;
      }

      public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
      }

      public int getEndOffsetTime() {
        return endOffsetTime;
      }

      public void setEndOffsetTime(int endOffsetTime) {
        this.endOffsetTime = endOffsetTime;
      }
    }
  }

  public static class TransitionsBean {

    /**
     * name : 百叶窗
     * index : 1
     * duration : 0.3
     */

    private String name;
    private int index;
    private double duration;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getIndex() {
      return index;
    }

    public void setIndex(int index) {
      this.index = index;
    }

    public double getDuration() {
      return duration;
    }

    public void setDuration(double duration) {
      this.duration = duration;
    }
  }

  public static class MusicsBean {

    /**
     * name : 江南最忆
     * duration : 10
     * mixWeight : 50
     * fileName : e0be53c02609c24f8a7b83fe9a83ef43.mp3
     * startTime : 0
     * cover : 1fb760891a50de904e5f7b880d3b41b1.jpg
     */

    private String name;
    private int duration;
    private int mixWeight;
    private String fileName;
    private int startTime;
    private String cover;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getDuration() {
      return duration;
    }

    public void setDuration(int duration) {
      this.duration = duration;
    }

    public int getMixWeight() {
      return mixWeight;
    }

    public void setMixWeight(int mixWeight) {
      this.mixWeight = mixWeight;
    }

    public String getFileName() {
      return fileName;
    }

    public void setFileName(String fileName) {
      this.fileName = fileName;
    }

    public int getStartTime() {
      return startTime;
    }

    public void setStartTime(int startTime) {
      this.startTime = startTime;
    }

    public String getCover() {
      return cover;
    }

    public void setCover(String cover) {
      this.cover = cover;
    }
  }

  public static class SectionsBean {

    /**
     * duration : 4
     * hint : 镜头向右环绕美食慢慢移动
     * startTime : 0
     */

    private int duration;
    private String hint;
    private int startTime;

    public int getDuration() {
      return duration;
    }

    public void setDuration(int duration) {
      this.duration = duration;
    }

    public String getHint() {
      return hint;
    }

    public void setHint(String hint) {
      this.hint = hint;
    }

    public int getStartTime() {
      return startTime;
    }

    public void setStartTime(int startTime) {
      this.startTime = startTime;
    }
  }
}
