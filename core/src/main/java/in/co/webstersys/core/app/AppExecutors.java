package in.co.webstersys.core.app;

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import kotlin.jvm.internal.Intrinsics;

@Singleton
public class AppExecutors {
    private Executor diskIO;
    private Executor networkIO;
    private Executor mainThread;
    @Inject
    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(),
             Executors.newFixedThreadPool(3),
                new MainThreadExecutor());
    }
    AppExecutors(@NotNull Executor diskIO, @NotNull Executor networkIO, @NotNull Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }
    @NotNull
    public final Executor diskIO() {
        return this.diskIO;
    }

    @NotNull
    public final Executor networkIO() {
        return this.networkIO;
    }

    @NotNull
    public final Executor mainThread() {
        return this.mainThread;
    }
    private static final class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        public void execute(@NotNull Runnable command) {
            Intrinsics.checkParameterIsNotNull(command, "command");
            this.mainThreadHandler.post(command);
        }
    }
}
