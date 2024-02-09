package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.UserPolicy;

public class VisionSubsystem extends EntechSubsystem{
    private static final boolean ENABLED = true;

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    //robot catches targets
    public double cameraX;
    public double cameraY;
    public boolean cameraTargets;

    //best target
    public double bestX;
    public double bestY;
    public boolean targetsPresent;

    //all tags (example, for now)
    public double tag8x;
    public double tag8y;

    private PhotonCamera bestCamera = new PhotonCamera("ShooterCamAprilTags");

    @Override
    public void initialize(){
        bestCamera.setPipelineIndex(0);
        CameraServer.startAutomaticCapture();
    }

    public void periodic(){
        VisionUpdate();
        DisplayStats();
    }
    public void VisionUpdate(){




        var result = bestCamera.getLatestResult();
        targetsPresent = result.hasTargets();

        if(targetsPresent){
            //listing the targets
            List<PhotonTrackedTarget> targets = result.getTargets();

            //looking in the grocery store
            for(int i = 0; i < targets.size(); i++){
                //check grocery aisle i
                int id = targets.get(i).getFiducialId();
                System.out.print(id);

                if(id == 8 && UserPolicy.speakerShoot){
                    cameraX = targets.get(i).getYaw();
                    cameraY = targets.get(i).getPitch();
                }

                if (id == 6 && UserPolicy.speakerShoot) {
                    cameraX = targets.get(i).getYaw();
                    cameraY = targets.get(i).getPitch();                
                }
            }
            
            PhotonTrackedTarget bestTarget = result.getBestTarget();
            bestX = bestTarget.getYaw();
            bestY = bestTarget.getPitch();
        }
    }
    public void DisplayStats(){
        SmartDashboard.putBoolean("Camera Target Detection", cameraTargets);
        SmartDashboard.putNumber("Camera X", cameraX);
        SmartDashboard.putNumber("Camera Y", cameraY);
    }
    //TODO: detect april tags with this code and be able to use this code for futher systems in the commands 
}