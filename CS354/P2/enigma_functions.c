#include "enigma.h"
/* Name: Ariel Fu
 * Net ID: 908 168 5910
 */
const char *ROTOR_CONSTANTS[] = {
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ", // Identity Rotor (index 0 - and useful for testing):
    "EKMFLGDQVZNTOWYHXUSPAIBRCJ",
    "AJDKSIRUXBLHWTMCQGZNPYFVOE",
    "BDFHJLCPRTXVZNYEIWGAKMUSQO",
    "ESOVPZJAYQUIRHXLNFTGKDCMWB",
    "VZBRGITYUPSDNHLXAWMJQOFECK",
    "JPGVOUMFYQBENHZRDKASXLICTW",
    "NZJHGRCXMYSWBOUFAIVLPEKQDT",
    "FKQHTLXOCBJSPDZRAMEWNIUYGV",
};

void getString(char str[]);
void Rotate_Rotor(int rotations, char rotor[]);
void Encrypt_One_Rotor(char rotor[], char msg[], char encrypted_msg[]);
void Decrypt_One_Rotor(char rotor[], char encrypted_msg[], char msg[]);

// get the user input and populates the passed in array
void getString(char str[])
{
    int length = 0;
    str[0] = '\0';
    // get user input
    scanf("%[^\n]%*c", str);
    for (int i = 0; str[i]; i++)
    {
        length++;
    }
    // terminate the string
    str[length] = '\0';
}

// rotate a rotor by the number of rotations passed in
void Rotate_Rotor(int rotations, char rotor[])
{
    // reduce the rotations by mod
    rotations = rotations % 26;
    // account for a negative number by 26 + num rotations to the right
    if (rotations < 0)
    {
        rotations += 26;
    }
    char temp[27];
    // put the curr char to its "rotated" spot in the temp array
    for (int i = 0; rotor[i] && i < 27; i++)
    {
        temp[(i + rotations) % 26] = rotor[i];
    }
    // copy the temp array back to the original rotor
    for (int i = 0; rotor[i]; i++)
    {
        rotor[i] = temp[i];
    }
}

// encrypts a message with one rotor
void Encrypt_One_Rotor(char rotor[], char msg[], char encrypted_msg[])
{
    int length = 0;
    for (int i = 0; msg[i]; i++)
    {
        length++;
        // if the curr char is a space, only add the space
        if (msg[i] == ' ')
        {
            encrypted_msg[i] = ' ';
        }
        else
        {
            // add the letter from the rotor to the encrypted message
            encrypted_msg[i] = rotor[msg[i] - 'A'];
        }
    }
    // terminate
    encrypted_msg[length] = '\0';
}

// decrypts the message with one rotor
void Decrypt_One_Rotor(char rotor[], char encrypted_msg[], char msg[])
{
    int length = 0;

    for (int i = 0; encrypted_msg[i]; i++)
    {
        length++;
        char currChar = encrypted_msg[i];
        // if the encrypted message contains a space, add the space to the decrypted message
        if (currChar == ' ')
        {
            msg[i] = ' ';
            continue;
        }
        int charIndex = -1;
        // find the location of the current char in the encryption rotor
        for (int j = 0; rotor[j]; j++)
        {
            char rotorChar = rotor[j];
            if (rotorChar == currChar)
            {
                charIndex = j;
                break;
            }
        }
        // map the location to the identity rotor and add it to the partially decrypted message
        msg[i] = ('A' + charIndex);
    }
    // terminate the decrypted message
    msg[length] = '\0';
}

// This method reads a character string from the keyboard and
// stores the string in the parameter msg.
// Keyboard input will be entirely uppercase and spaces followed by
// the enter key.
// The string msg should contain only uppercase letters spaces and
// terminated by the '\0' character
// Do not include the \n entered from the keyboard
void Get_Message(char msg[])
{
    // get the input and return the message
    getString(msg);
    int length = 0;
    for (int i = 0; msg[i] && i < 79; i++)
    {
        length++;
    }
    // terminate at 80 chars characters
    msg[length] = '\0';
    return;
}

// This function reads up to 4 characters from the keyboard
// These characters will be only digits 1 through 8. The user
// will press enter when finished entering the digits.
// The rotor string filled in by the function should only contain
// the digits terminated by the '\0' character. Do not include
// the \n entered by the user.
// The function returns the number of active rotors.
int Get_Which_Rotors(char which_rotors[])
{
    // get the input from the user
    getString(which_rotors);
    int length = 0;
    // count the number of rotors
    for (int i = 0; which_rotors[i] && i < 4; i++)
    {
        length++;
    }
    // terminate which_rotors to only allow 4 or less rotors
    which_rotors[length] = '\0';
    // return the number of rotors
    return length;
}

// This function reads an integer from the keyboard and returns it
// This number represents the number of rotations to apply to the
// encryption rotors.  The input will be between 0 and 25 inclusive
int Get_Rotations()
{
    // get the input from the user
    char input[2];
    getString(input);
    int length = 0;
    for (int i = 0; input[i] && i < 2; i++)
    {
        length++;
    }
    input[length] = '\0';
    // conver the input to an integer
    int rotations = atoi(input);
    // return the int value of the input
    return rotations;
}

// This function copies the rotors indicated in the which_rotors string
// into the encryption_rotors.  For example if which rotors contains the string
// {'3', '1', '\0'} Then this function copies the third and first rotors into the
// encryption rotors array in positions 0 and 1.
// encryptions_rotors[0] = "BDFHJLCPRTXVZNYEIWGAKMUSQO"
// encryptions_rotors[1] = "EKMFLGDQVZNTOWYHXUSPAIBRCJ"
void Set_Up_Rotors(char encryption_rotors[4][27], char which_rotors[5])
{
    // go through which rotors
    for (int i = 0; which_rotors[i]; i++)
    {
        // get which rotoer to use from which_rotor
        int rotorIndex = which_rotors[i] - '0';
        int length = 0;
        // copy the rotor const to the list of encryption rotors to use
        for (int j = 0; ROTOR_CONSTANTS[rotorIndex][j]; j++)
        {
            encryption_rotors[i][j] = ROTOR_CONSTANTS[rotorIndex][j];
            length++;
        }
        // terminate the curr encryption rotor
        encryption_rotors[i][length] = '\0';
    }
    return;
}

// This function rotates the characters in each of the active encryption rotors
// to the right by rotations.  For example if rotations is 3 encryption_rotors[0]
// contains "BDFHJLCPRTXVZNYEIWGAKMUSQO" then after rotation this row will contain
// SQOBDFHJLCPRTXVZNYEIWGAKMU.  Apply the same rotation to all for strings in
// encryption_rotors
void Apply_Rotation(int rotations, char encryption_rotors[4][27])
{ // for every rotor in encryption_rotor (regardless if it is being used)
    for (int i = 0; i < 4; i++)
    {
        // rotate the curr rotor by "rotations"
        Rotate_Rotor(rotations, encryption_rotors[i]);
    }
    return;
}

// This function takes a string msg and applys the enigma machine to encrypt the message
// The encrypted message is stored in the string encryped_msg
// Do not change spaces, make sure your encryped_msg is a \0 terminated string
void Encrypt(char encryption_rotors[4][27], int num_active_rotors, char msg[], char encrypted_msg[])
{
    // set the temp to the max size of message
    char temp[80];
    // copy the message to the temp array
    strncpy(temp, msg, 80);
    // encrypt data
    for (int i = 0; i < num_active_rotors; i++)
    {
        // encrypt the temp message and store in encrypted_msg
        Encrypt_One_Rotor(encryption_rotors[i], temp, encrypted_msg);
        // copy the message encrypted with one rotor to the temp and continue encryption
        strcpy(temp, encrypted_msg);
    }
    return;
}

// This function takes a string msg and applys the enigma machine to decrypt the message
// The encrypted message is stored in the string encryped_msg and the decrypted_message
// is returned as a call by reference variable
// remember the encryption rotors must be used in the reverse order to decrypt a message
// Do not change spaces, make sure your decrytped_msg is a \0 terminated string
void Decrypt(char encryption_rotors[4][27], int num_active_rotors, char encrypted_msg[], char decrypted_msg[])
{
    // set the temp to the max size of the message
    char temp[80];
    // copy the encrypted message to the temp array
    strcpy(temp, encrypted_msg);
    // starting from the "end" of the encrypted rotors, work backwards
    for (int i = num_active_rotors - 1; i >= 0; i--)
    {
        // decrypt the temp message and store in decrypted_mgs
        Decrypt_One_Rotor(encryption_rotors[i], temp, decrypted_msg);
        // copy the partially decrypted message with one rotor to the temp and continue decryption
        strcpy(temp, decrypted_msg);
    }
    return;
}
