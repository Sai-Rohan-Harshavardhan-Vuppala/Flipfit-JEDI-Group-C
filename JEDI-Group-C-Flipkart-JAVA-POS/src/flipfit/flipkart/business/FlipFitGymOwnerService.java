package flipfit.flipkart.business;

import flipfit.flipkart.bean.FlipFitGymOwner;


    public class FlipFitGymOwnerService {
        public FlipFitGymOwner createGymOwner(){
            FlipFitGymOwner gymOwner = new FlipFitGymOwner("Sankalp", "sankalpg38@gmail.com", "sankalpg38", "mypassword");
            System.out.println("Gym owner" + gymOwner + "created");
            return gymOwner;
        }
        public void createGym(){

        }
        public void updateGym(){

        }

        public void deleteGym(){

        }

        public void getGymByGymId(){

        }
    }

