package model;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartupInitializer {

    @Inject
    private MemberFacade memberFacade;

    @PostConstruct
    public void init() {
        System.out.println("StartupInitializer: Initializing pre-registered staff...");

        try {
            // Check if pre-registered staff exists by name "staff"
            Member staff = memberFacade.findByName("staff");
            if (staff == null) {
                // Create pre-registered staff with username "staff"
                Member preRegisteredStaff = new Member(
                    "staff",  // Username
                    "staff123", // Password
                    "staff",   // Role
                    "male",    // Gender
                    1234567890L, // Phone
                    "S1234567", // IC
                    "staff@system.com", // Email
                    "System Address" // Address
                );
                memberFacade.create(preRegisteredStaff);
                System.out.println("Pre-registered staff created successfully!");
            } else {
                System.out.println("Pre-registered staff already exists.");
            }
        } catch (Exception e) {
            System.err.println("Error creating pre-registered staff: " + e.getMessage());
            e.printStackTrace();
        }
    }
}