package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbAutoUpCommand extends EntechCommand {
    
    private final ClimbSubsystem climb;

    public ClimbAutoUpCommand(ClimbSubsystem climb) {
        this.climb = climb;
    }

     @Override
    public void initialize() {
        UserPolicy.autoUp = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.autoUp) {
            climb.AutoUp();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.autoUp = false;
        climb.ClimbLeftDisable();
        climb.ClimbRightDisable();
    }   
}
