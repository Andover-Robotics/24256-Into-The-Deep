package org.firstinspires.ftc.teamcode.Util;
import java.util.function.Supplier;

public interface InputColumnResponder {
    InputColumnResponder register(Supplier<Boolean> predicate, Runnable triggerCallback);
    void update();
    void clearRegistry();
}