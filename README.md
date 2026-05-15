# ATM Simulator

## Table of Contents
- [Introduction](#introduction)
- [Installation Guide](#installation-guide)
- [Usage](#usage)

---

## Introduction

This ATM Simulator is a Java Swing-based banking application that simulates the following functionality of a real ATM system:


- Authenticate using a card number and password
- Withdraw money
- Deposit money
- Transfer money between accounts
- Check account balances

The simulator also supports multiple languages including:
- English
- 한국어
- 中文

Account information is stored locally using encoded text files.

---

## Installation Guide

### Requirements

Before running the project, ensure you have:
- Java JDK 8 or higher
- IntelliJ IDEA or another Java IDE (optional)

---

## Usage

### Running the Program

1. Open the project in IntelliJ IDEA or another Java IDE.
2. Run `Mainframe.java`.
3. The ATM Simulator window should appear. (If there are issues with the background images or icons not appearing, ensure that the `images` folder is correctly located inside the `src` directory and that the image paths match the file names exactly.)
---

### Logging In

1. Choose a language.
2. Enter the card number and password
3. Access ATM features from the main menu.

---

### Sample Account

Card Number:
```text
123456789
```

Password:
```text
01234
```

---

### Creating a New Account

Accounts are stored as `.txt` files inside the `accounts` folder.

The filename represents the card number.

Example:

```text
123456789.txt
```

Inside the file, account information is stored using Base64 encoding.

Structure:

```text
<Name>
<Password>
<Balance>
```

Example encoded file:

```text
UGF1bA==
MDEyMzQ=
MTAw
```

Decoded values:

```text
Name: Paul
Password: 01234
Balance: 100
```

To create a new account:
1. Create a new `.txt` file inside the `accounts` folder.
2. Name the file using the desired card number.
3. Encode the account information using Base64.
4. Add the encoded values line by line into the file.
