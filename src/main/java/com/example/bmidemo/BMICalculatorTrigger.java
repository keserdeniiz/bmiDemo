package com.example.bmidemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BMICalculatorTrigger {

    public static void main(String[] args) {
        // Set up database connection parameters
        String url = "jdbc:postgresql://localhost:5433/BMI";
        String username = "postgres";
        String password = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create the trigger function
            Statement statement = connection.createStatement();
            String triggerFunctionSQL = "CREATE OR REPLACE FUNCTION calculate_bmi_trigger()\n" +
                    "RETURNS TRIGGER AS $$\n" +
                    "BEGIN\n" +
                    "    INSERT INTO BMI_Result (user_id, classification, index_range)\n" +
                    "    VALUES (NEW.user_id, \n" +
                    "            CASE\n" +
                    "                WHEN NEW.bmi < 16 THEN 'Severe Thinness'\n" +
                    "                WHEN NEW.bmi < 17 THEN 'Moderate Thinness'\n" +
                    "                WHEN NEW.bmi < 18.5 THEN 'Mild Thinness'\n" +
                    "                WHEN NEW.bmi < 25 THEN 'Normal'\n" +
                    "                WHEN NEW.bmi < 30 THEN 'Overweight'\n" +
                    "                WHEN NEW.bmi < 35 THEN 'Obese Class I'\n" +
                    "                WHEN NEW.bmi < 40 THEN 'Obese Class II'\n" +
                    "                ELSE 'Obese Class III'\n" +
                    "            END,\n" +
                    "            CASE\n" +
                    "                WHEN NEW.bmi < 16 THEN '< 16'\n" +
                    "                WHEN NEW.bmi < 17 THEN '16 - 17'\n" +
                    "                WHEN NEW.bmi < 18.5 THEN '17 - 18.5'\n" +
                    "                WHEN NEW.bmi < 25 THEN '18.5 - 25'\n" +
                    "                WHEN NEW.bmi < 30 THEN '25 - 30'\n" +
                    "                WHEN NEW.bmi < 35 THEN '30 - 35'\n" +
                    "                WHEN NEW.bmi < 40 THEN '35 - 40'\n" +
                    "                ELSE '> 40'\n" +
                    "            END);\n" +
                    "    RETURN NEW;\n" +
                    "END;\n" +
                    "$$ LANGUAGE plpgsql;";
            statement.executeUpdate(triggerFunctionSQL);

            // Create the trigger
            String triggerSQL = "CREATE TRIGGER bmi_trigger\n" +
                    "AFTER INSERT OR UPDATE ON \"BMI_User\"\n" +
                    "FOR EACH ROW\n" +
                    "EXECUTE FUNCTION calculate_bmi_trigger();";
            statement.executeUpdate(triggerSQL);

            // Close the database connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
