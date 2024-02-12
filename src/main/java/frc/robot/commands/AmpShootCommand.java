package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ShooterSubsystem;

public class AmpShootCommand extends EntechCommand {

    private final ShooterSubsystem shoot;

    public AmpShootCommand(ShooterSubsystem shoot) {
        // TODO: Do some funky stuff, the same as the speaker shoot command, to get auto-feeding working
        this.shoot = shoot;
    }

     @Override
    public void initialize() {
        UserPolicy.ampShoot = true;
        UserPolicy.shootUptoSpeed = false;
    }

    @Override
    public void execute(){
        if (UserPolicy.ampShoot) {
            shoot.AmpCommand();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.ampShoot = false;
        shoot.DisableShoot();
    }   
}
