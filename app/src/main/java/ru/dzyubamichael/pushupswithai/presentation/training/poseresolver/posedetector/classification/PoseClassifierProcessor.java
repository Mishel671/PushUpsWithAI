package ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.posedetector.classification;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Looper;

import androidx.annotation.WorkerThread;

import com.google.common.base.Preconditions;
import com.google.mlkit.vision.pose.Pose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PoseClassifierProcessor {
    private static final String POSE_SAMPLES_FILE = "pose/fitness_pose.csv";
    private static final String PUSHUPS_CLASS = "pushups_down";
    private final boolean isStreamMode;

    private EMASmoothing emaSmoothing;
    private List<RepetitionCounter> repCounters;
    private PoseClassifier poseClassifier;
    private int lastRepResult = 0;

    @WorkerThread
    public PoseClassifierProcessor(Context context, boolean isStreamMode) {
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper());
        this.isStreamMode = isStreamMode;
        if (isStreamMode) {
            emaSmoothing = new EMASmoothing();
            repCounters = new ArrayList<>();
        }
        loadPoseSamples(context);
    }

    private void loadPoseSamples(Context context) {
        List<PoseSample> poseSamples = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(POSE_SAMPLES_FILE)));
            String csvLine = reader.readLine();
            while (csvLine != null) {
                PoseSample poseSample = PoseSample.getPoseSample(csvLine, ",");
                if (poseSample != null) {
                    poseSamples.add(poseSample);
                }
                csvLine = reader.readLine();
            }
        } catch (IOException e) {
            ;
        }
        poseClassifier = new PoseClassifier(poseSamples);
        if (isStreamMode) {
            repCounters.add(new RepetitionCounter(PUSHUPS_CLASS));

        }
    }

    @WorkerThread
    public List<Integer> getPoseResult(Pose pose) {
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper());
        List<Integer> result = new ArrayList<>();
        ClassificationResult classification = poseClassifier.classify(pose);

        if (isStreamMode) {
            classification = emaSmoothing.getSmoothedResult(classification);

            for (RepetitionCounter repCounter : repCounters) {
                int repsBefore = repCounter.getNumRepeats();
                int repsAfter = repCounter.addClassificationResult(classification);
                if (repsAfter > repsBefore) {
                    lastRepResult = repsAfter;
                    break;
                }
            }
            result.add(lastRepResult);
        }

        return result;
    }

}
