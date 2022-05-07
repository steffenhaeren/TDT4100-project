package satbank;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Our datahandler which writes and gatheres information from our database.
 */
public class DataHandler implements SaveInterface {

    private FileWriter fWriter;
    private PrintWriter pWriter;

    private static String DIR = System.getProperty("user.dir") + "/src/main/resources/satbank/db.txt";

    /**
     * Adds an account to our database.
     */
    @Override
    public void addAcc(User user, Account account) {

        try {
            Path path = (Path) Paths.get(DIR);
            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).split(";")[0].equals(user.getName())) {
                    String addAcc = account.getName() + ";" + account.getType() + ";" + account.getBalance() + ";";
                    fileContent.set(i, fileContent.get(i) + addAcc);
                    break;
                }
            }

            Files.write(path, fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes an account from our database.
     */
    @Override
    public void removeAcc(User user, String accName) {
        try {
            Path path = (Path) Paths.get(DIR);
            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).split(";")[0].equals(user.getName())) {
                    String[] newContent = fileContent.get(i).split(";");
                    String temp = newContent[0] + ";" + newContent[1] + ";" + newContent[2] + ";";
                    for (int x = 3; x < newContent.length; x += 3) {
                        if (newContent[x].equals(accName)) {
                            continue;
                        } else {
                            temp += newContent[x] + ";" + newContent[x + 1] + ";" + newContent[x + 2] + ";";
                        }
                    }
                    fileContent.set(i, temp);

                }
            }
            Files.write(path, fileContent, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Registers a new user in database.
     */
    @Override
    public void newUser(String name, String password, String email) {
        try {
            File file = new File(DIR);
            fWriter = new FileWriter(file, true); // True betyr at den ikke sletter gammel informasjon i filen
            pWriter = new PrintWriter(fWriter);
            String printString = name + ";" + password + ";" + email + ";";
            pWriter.println(printString);
            pWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Private helping function.
     * 
     * @param a
     * @param b
     * @param add
     * @return an int to be used.
     */
    private int calculateBalance(int a, int b, boolean add) {
        if (add) {
            return a + b;
        }
        return a - b;
    }

    /**
     * Edits a balance in our database. Is fired after we have changed the balance
     * in our user object.
     * Boolean used to check if we are adding or removing funds.
     */
    @Override
    public void editBalance(User user, String accName, int balance, boolean add) {

        try {
            Path path = (Path) Paths.get(DIR);
            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).split(";")[0].equals(user.getName())) {
                    String[] newContent = fileContent.get(i).split(";");
                    String temp = newContent[0] + ";" + newContent[1] + ";" + newContent[2] + ";";
                    for (int x = 3; x < newContent.length; x += 3) {
                        int newBalance = calculateBalance(Integer.parseInt(newContent[x + 2]), balance, add);
                        if (newContent[x].equals(accName)) {
                            temp += newContent[x] + ";" + newContent[x + 1] + ";"
                                    + Integer.toString(newBalance) + ";";
                        } else {
                            temp += newContent[x] + ";" + newContent[x + 1] + ";" + newContent[x + 2] + ";";
                        }
                    }
                    fileContent.set(i, temp);
                }
            }
            Files.write(path, fileContent, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Is used to check if a username is taken in our database.
     * 
     * @param username for checking in database.
     * @return true if the account exists, else false.
     */
    public boolean checkIfExists(String username) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(DIR));
            String line = reader.readLine();
            while (line != null) {
                String[] userInfo;
                userInfo = line.split(";");
                if (username.equals(userInfo[0])) {
                    reader.close();
                    return true;
                }
                line = reader.readLine();

            }
            reader.close();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Used to check if the login is valid, ie if the password and username is
     * correct.
     * 
     * @param username to see username.
     * @param password to see password.
     * @return a String because we need multiple returnvalues and not a boolean
     *         yes/no.
     */
    public String checkLoginInfo(String username, String password) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(DIR));
            String line = reader.readLine();
            while (line != null) {
                String[] userInfo;
                userInfo = line.split(";");
                if (username.equals(userInfo[0])) {
                    if (password.equals(userInfo[1])) {
                        reader.close();
                        return "Login";
                    } else {
                        reader.close();
                        return "Wrong password";
                    }
                }
                line = reader.readLine();

            }
            reader.close();
            return "No such username";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Something went wrong";
    }

    /**
     * Is used on login to provide the accounts from our database.
     * 
     * @param username to find the right user.
     * @return ArrayList<Accounts> to use in User's constructor.
     */
    public ArrayList<Account> getAccounts(String username) {
        BufferedReader reader;
        ArrayList<Account> returnList = new ArrayList<Account>();
        try {
            reader = new BufferedReader(new FileReader(DIR));
            String line = reader.readLine();
            while (line != null) {
                String[] userInfo;
                userInfo = line.split(";");
                if (username.equals(userInfo[0])) {
                    reader.close();
                    for (int i = 3; i < userInfo.length - 1; i += 3) {
                        String name = userInfo[i];
                        char type = userInfo[i + 1].charAt(0);
                        int balance = Integer.parseInt(userInfo[i + 2]);
                        Account acount = new Account(name, type, balance);
                        returnList.add(acount);

                    }
                    return returnList;

                }
                line = reader.readLine();

            }
            reader.close();
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Is used on login to provide email saved in our database.
     * 
     * @param username for finding the right email.
     * @return The email to use in Users constructor.
     */
    public String getEmail(String username) {
        BufferedReader reader;
        String email = "";
        try {
            reader = new BufferedReader(new FileReader(DIR));
            String line = reader.readLine();
            while (line != null) {
                String[] userInfo;
                userInfo = line.split(";");
                if (username.equals(userInfo[0])) {
                    email += userInfo[2];
                    reader.close();
                    return email;
                }
                line = reader.readLine();
            }
            reader.close();
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
