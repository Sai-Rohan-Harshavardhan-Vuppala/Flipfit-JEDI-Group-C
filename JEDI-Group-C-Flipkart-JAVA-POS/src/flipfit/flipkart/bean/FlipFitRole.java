package flipfit.flipkart.bean;


public class FlipFitRole{
    private int roleId;
    private String roleName;
    /*
    1 -> admin
    2 -> customer
    3 -> gymOwner
     */
    public FlipFitRole(int roleId, String roleName){
        this.roleId = roleId;
        this.roleName = roleName;
    }


}
