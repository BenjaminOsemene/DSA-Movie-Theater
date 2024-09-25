import java.util.ArrayList;
import java.util.Scanner;

// Defines class and constants for movie theater
public class MovieTheater {
    private static int ROWS = 10;
    private static int NUM_SEATS_IN_ROW = 20;
    
    //2D array for the seating chart
    private static char[][] seatingChart = new char[ROWS][NUM_SEATS_IN_ROW];
    
    // ArrayList to store reservation codes
    private static ArrayList<String> reservations = new ArrayList<>();
    
    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeSeatingChart();
        
        // Main program loop
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            // Switch statement for user choices
            switch (choice) {
                case 1:
                    bookSeat();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    showSeatingChart();
                    break;
                case 4:
                    System.out.println("Thank you for using our booking system!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Initialize all seats as available 
    private static void initializeSeatingChart() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < NUM_SEATS_IN_ROW; j++) {
                seatingChart[i][j] = 'A';
            }
        }
    }

    // Displaying  the main menu options
    private static void displayMenu() {
        System.out.println("\n--- Movie Theater Booking System ---");
        System.out.println("1. Book a seat");
        System.out.println("2. Cancel reservation");
        System.out.println("3. View seating chart");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Booking seat
    private static void bookSeat() {
        System.out.print("Enter row number (1-" + ROWS + "): ");
        int row = scanner.nextInt() - 1;
        System.out.print("Enter seat number (1-" + NUM_SEATS_IN_ROW + "): ");
        int seat = scanner.nextInt() - 1;
        scanner.nextLine(); 

        if (row < 0 || row >= ROWS || seat < 0 || seat >= NUM_SEATS_IN_ROW) {
            System.out.println("Invalid row or seat number.");
            return;
        }

        if (seatingChart[row][seat] == 'B') {
            System.out.println("This seat is already booked.");
        } else {
            seatingChart[row][seat] = 'B';
            String reservation = "R" + (row + 1) + "S" + (seat + 1);
            reservations.add(reservation);
            System.out.println("Seat booked successfully. Reservation code is: " + reservation);
        }
    }

    // Cancel reservation
    private static void cancelReservation() {
        System.out.print("Enter your reservation code: ");
        String code = scanner.nextLine().toUpperCase();

        if (reservations.remove(code)) {
            int row = Character.getNumericValue(code.charAt(1)) - 1;
            int seat = Character.getNumericValue(code.charAt(3)) - 1;
            seatingChart[row][seat] = 'A';
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Reservation not found.");
        }
    }

    // Displaying current seat chart
    private static void showSeatingChart() {
        System.out.println("\nCurrent Seating Chart:");
        System.out.print("   ");
        for (int i = 1; i <= NUM_SEATS_IN_ROW; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            System.out.printf("%2d ", i + 1);  
            for (int j = 0; j < NUM_SEATS_IN_ROW; j++) {
                System.out.printf("%2c ", seatingChart[i][j]);
            }
            System.out.println();
        }
        
        System.out.println("A: Available, B: Booked");
    }
}





