package ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.posedetector.classification;


public class RepetitionCounter {
  private static final float DEFAULT_ENTER_THRESHOLD = 6f;
  private static final float DEFAULT_EXIT_THRESHOLD = 4f;

  private final String className;
  private final float enterThreshold;
  private final float exitThreshold;

  private int numRepeats;
  private boolean poseEntered;

  public RepetitionCounter(String className) {
    this(className, DEFAULT_ENTER_THRESHOLD, DEFAULT_EXIT_THRESHOLD);
  }

  public RepetitionCounter(String className, float enterThreshold, float exitThreshold) {
    this.className = className;
    this.enterThreshold = enterThreshold;
    this.exitThreshold = exitThreshold;
    numRepeats = 0;
    poseEntered = false;
  }

  public int addClassificationResult(ClassificationResult classificationResult) {
    float poseConfidence = classificationResult.getClassConfidence(className);

    if (!poseEntered) {
      poseEntered = poseConfidence > enterThreshold;
      return numRepeats;
    }

    if (poseConfidence < exitThreshold) {
      numRepeats++;
      poseEntered = false;
    }

    return numRepeats;
  }

  public String getClassName() {
    return className;
  }

  public int getNumRepeats() {
    return numRepeats;
  }
}
