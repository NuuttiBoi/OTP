## <img width="59" alt="Screenshot 2024-10-07 at 19 37 13" src="https://github.com/user-attachments/assets/c328a489-fee1-44dc-bc29-e08edfd8b203">## Whip Rent


## Team Members
- Nuutti Turunen
- Nahal Asif
- Nimo Hanna Farah

## Idea

WhipRent is a modern car rental app designed to streamline the vehicle rental process. It offers a user-friendly platform where users can browse available cars, make reservations, and manage bookings from any device. Key features include real-time vehicle availability, a simple registration process, and an intuitive interface for easy rental management.

Our mission is to enhance the car rental experience by making it fast, flexible, and accessible for both individuals and businesses seeking convenient transportation options.

## Target Audience

WhipRent serves the following user groups:
- **Individual Renters**: People renting cars for personal use, such as vacations or errands.
- **Business Travelers**: Professionals requiring reliable rentals for business trips.
- **Tourists**: Visitors who prefer the freedom of renting a car to explore a city or region.
- **Local Residents**: Individuals who need temporary transportation but don't own a vehicle.
- **Event Planners**: Organizers needing multiple vehicles for events or guest logistics.

By catering to these diverse groups, WhipRent offers a flexible and convenient car rental solution.

## Why This Project is Beneficial

WhipRent provides several advantages:
1. **User-Friendly**: Simple and intuitive interface for easy car rental.
2. **Time-Saving**: Online reservations eliminate the need for in-person processes.
3. **Accessibility**: Cars can be rented anytime, anywhere, increasing user flexibility.
4. **Real-Time Availability**: Up-to-date information on car availability ensures better planning.
5. **Eco-Friendly**: Encourages shared vehicle usage, reducing the demand for private cars and promoting sustainable transportation.

This project improves user experience and offers business opportunities in the car rental industry.

## Features

WhipRent includes the following functionalities:
1. **User Accounts**: Register, log in, and manage user profiles.
2. **Car Browsing & Search**: Browse available cars and search based on preferences.
3. **Car Reservations**: Book a vehicle for specific time periods directly through the app.
4. **Rental History**: View past and upcoming bookings.
5. **Rental Modification**: Easily modify or cancel future bookings.
6. **Admin Control Panel**: Admins can manage car listings, monitor rentals, and handle user accounts.

## Instructions for Use

1. **Register or Log In**: Create a new account or log in with existing credentials.
2. **Browse Cars**: Use the search feature to find a suitable vehicle.
3. **Reserve a Car**: Select a vehicle and confirm your booking.
4. **Manage Bookings**: Check your rental history and modify future reservations as needed.
5. **Admin Functions**: Admins can update car listings, oversee bookings, and manage users.

Follow these steps to easily rent cars using the WhipRent app.


# Running JavaFX Application in Docker

## Prerequisites

Before running your JavaFX application in Docker with GUI support, ensure you have the following installed:

- **Docker**: Make sure you have Docker installed on your machine.
- **X Server**: You need to have an X server running:
  - For **Windows**, use [VcXsrv](https://sourceforge.net/projects/vcxsrv/).
  - For **macOS**, use [XQuartz](https://www.xquartz.org/).

## Step 1: Start the X Server

### For Windows (Using VcXsrv)

1. **Download and Install VcXsrv**:
   - Download from [VcXsrv's official site](https://sourceforge.net/projects/vcxsrv/).
   - Run the installer and complete the installation.

2. **Launch VcXsrv**:
   - Start **XLaunch** from the Start Menu.
   - Choose **Multiple windows** and click **Next**.
   - Keep the default display number (usually `0`) and click **Next**.
   - Uncheck **"Enable access control"** and click **Finish**.

### For macOS (Using XQuartz)

1. **Download and Install XQuartz**:
   - Download from [XQuartz's website](https://www.xquartz.org/).
   - Run the installer and follow the instructions.

2. **Launch XQuartz**:
   - Open XQuartz from your Applications folder.
   - Go to **XQuartz menu** > **Preferences** > **Security** tab.
   - Check **"Allow connections from network clients"**.

## Step 2: Allow X11 Access

Run the following command in your terminal to allow X11 access (only required for Linux):

```bash
xhost +local:docker
````

## Step 3> Run the image

```bash
set DISPLAY=host.docker.internal:0
docker run -e DISPLAY=host.docker.internal:0 -v /tmp/.X11-unix:/tmp/.X11-unix --rm nuuttiboi/project


 
