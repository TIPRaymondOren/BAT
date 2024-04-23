package com.example.bat;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReportScreen extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextContact;
    private EditText editTextPlateNumber;
    private Spinner spinnerVehicleType;
    private CheckBox checkBoxFlatTire;
    private CheckBox checkBoxGas;
    private CheckBox checkBoxBattery;
    private EditText editTextAdditionalInfo;
    private Button buttonSubmit;

    private EditText editTextBrand;
    private EditText editTextModel;
    private EditText editTextColor;
    private EditText editTextLandmark;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_screen);

        editTextName = findViewById(R.id.editTextName);
        editTextContact = findViewById(R.id.editTextContact);
        editTextPlateNumber = findViewById(R.id.editTextPlateNumber);
        spinnerVehicleType = findViewById(R.id.spinnerVehicleType);
        checkBoxFlatTire = findViewById(R.id.checkBoxFlatTire);
        checkBoxGas = findViewById(R.id.checkBoxGas);
        checkBoxBattery = findViewById(R.id.checkBoxBattery);
        editTextAdditionalInfo = findViewById(R.id.editTextAdditionalInfo);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        editTextBrand = findViewById(R.id.editTextBrand);
        editTextModel = findViewById(R.id.editTextModel);
        editTextColor = findViewById(R.id.editTextColor);
        editTextLandmark = findViewById(R.id.editTextLandmark);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.vehicle_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerVehicleType.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        // Change the myRef variable to point to the root of the database
        myRef = database.getReference();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = editTextName.getText().toString();
                    String contact = editTextContact.getText().toString();
                    String plateNumber = editTextPlateNumber.getText().toString();
                    Object vehicleTypeObject = spinnerVehicleType.getSelectedItem();
                    String vehicleType = vehicleTypeObject != null ? vehicleTypeObject.toString() : "";
                    boolean flatTire = checkBoxFlatTire.isChecked();
                    boolean gas = checkBoxGas.isChecked();
                    boolean battery = checkBoxBattery.isChecked();
                    String additionalInfo = editTextAdditionalInfo.getText().toString();
                    String brand = editTextBrand.getText().toString();
                    String model = editTextModel.getText().toString();
                    String color = editTextColor.getText().toString();
                    String landmark = editTextLandmark.getText().toString();

                    // Validate the input
                    if (name.isEmpty() || contact.isEmpty() || plateNumber.isEmpty() || vehicleType.equals("Select Vehicle Type") || brand.isEmpty() || model.isEmpty() || color.isEmpty() || landmark.isEmpty()) {
                        Toast.makeText(ReportScreen.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Validate the contact number
                    if (!contact.startsWith("09") || contact.length() != 11) {
                        Toast.makeText(ReportScreen.this, "Invalid Phone Number, Please input a correct one", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Validate the plate number
                    if (!plateNumber.matches("[A-Za-z]{3}-[0-9]{4}")) {
                        Toast.makeText(ReportScreen.this, "Plate number invalid should have first 3 letters and 4 digits", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Process the input
                    // ...

                    // Send the data to Firebase Realtime Database
                    myRef.push().setValue(new Report(name, contact, plateNumber, vehicleType, flatTire, gas, battery, additionalInfo, brand, model, color, landmark));

                    Toast.makeText(ReportScreen.this, "Report submitted successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ReportScreen.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

class Report {
    public String name;
    public String contact;
    public String plateNumber;
    public String vehicleType;
    public boolean flatTire;
    public boolean gas;
    public boolean battery;
    public String additionalInfo;
    public String brand;
    public String model;
    public String color;
    public String landmark;

    public Report(String name, String contact, String plateNumber, String vehicleType, boolean flatTire, boolean battery, boolean gas, String additionalInfo, String brand, String model, String color, String landmark) {
        this.name = name;
        this.contact = contact;
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
        this.flatTire = flatTire;
        this.gas = gas;
        this.battery = battery;
        this.additionalInfo = additionalInfo;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.landmark = landmark;
    }
}
