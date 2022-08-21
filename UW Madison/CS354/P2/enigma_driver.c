#include "enigma.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

const char *ROT[] = {
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

int compareRotors(char expected[], char output[])
{
    int length = 0;
    for (int i = 0; output[i]; i++)
    {
        // printf("%c %c \n", expected[i], output[i]);
        if (expected[i] != output[i])
        {
            return 0;
        }
        length++;
    }
    if (expected[length] != output[length])
    {
        return 0;
    }
    return 1;
}

int main()
{
    char message[80];
    char encrypted_message[80];
    char decrypted_message[80];
    char which_rotors[5];
    char encryption_rotors[4][27];
    int rotations;
    int num_active_rotors;

    // validate get message returns at most 80 chars and a null char
    // Get_Message(message);
    // int length = 0;
    // for (int i = 0; message[i]; i++)
    // {
    //     length++;
    // }
    // printf("MESSAGE: -%s- \t last char: %c\n", message, message[length]);

    // Get_Which_Rotors(which_rotors);
    // int length = 0;
    // for (int i = 0; which_rotors[i]; i++)
    // {
    //     length++;
    // }
    // printf("ROTORS: -%s-\t last char: %c\n", which_rotors, which_rotors[length]);

    int rot = Get_Rotations();
    printf("rotation: %d", rot);

    // copy no rotors
    // which_rotors[0] = '3';
    // which_rotors[1] = '4';
    // // which_rotors[2] = '5';
    // // which_rotors[3] = '6';
    // which_rotors[2] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // for (int i = 0; i < 2; i++)
    // {
    //     printf("%dth rotor             : -%s- \n", i, encryption_rotors[i]);
    //     printf("%dth rotor in constants: -%s-\n\n", i, ROT[i + 3]);
    // }

    which_rotors[0] = '3';
    which_rotors[1] = '\0';
    // which_rotors[2] = '\0';
    // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // printf("rotated 3: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -   %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(4, encryption_rotors);
    // printf("rotated 4: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -    %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(5, encryption_rotors);
    // printf("rotated 5: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -     %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(6, encryption_rotors);
    // printf("rotated 6: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -      %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(7, encryption_rotors);
    // printf("rotated 7: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -       %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(8, encryption_rotors);
    // printf("rotated 8: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -        %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(9, encryption_rotors);
    // printf("rotated 9: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -         %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(10, encryption_rotors);
    // printf("rotated 0: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -          %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(11, encryption_rotors);
    // printf("rotated 1: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -           %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(12, encryption_rotors);
    // printf("rotated 2: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -            %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(13, encryption_rotors);
    // printf("rotated 3: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -             %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(14, encryption_rotors);
    // printf("rotated 4: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -              %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(15, encryption_rotors);
    // printf("rotated 5: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -               %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(16, encryption_rotors);
    // printf("rotated 6: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -                %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(17, encryption_rotors);
    // printf("rotated 7: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -                 %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(18, encryption_rotors);
    // printf("rotated 8: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -                  %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(19, encryption_rotors);
    // printf("rotated 9: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -                   %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(20, encryption_rotors);
    // printf("rotated 0: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -                    %s-\n", ROT[3]);

    // which_rotors[0] = '3';
    // which_rotors[1] = '\0';
    // // which_rotors[2] = '\0';
    // // apply rotations
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(21, encryption_rotors);
    // printf("rotated 1: -%s-\n", encryption_rotors[0]);
    // printf("orig:    = -                     %s-\n", ROT[3]);
    // printf("rotated 2: -%s-\n", encryption_rotors[1]);
    // printf("orig:    = -   %s-", ROT[2]);

    // encryption

    // apply rotations
    // which_rotors[0] = '1';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // char msg[] = "IRONMAN VS BATMAN";
    // printf("og -%s-\n", msg);
    // Encrypt(encryption_rotors, 1, msg, encrypted_message);
    // printf("#1 -%s-\n", encrypted_message);

    // which_rotors[0] = '2';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Encrypt(encryption_rotors, 1, encrypted_message, encrypted_message);
    // printf("#2 -%s-\n", encrypted_message);

    // which_rotors[0] = '8';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Encrypt(encryption_rotors, 1, encrypted_message, encrypted_message);
    // printf("#3 -%s-\n", encrypted_message);

    // which_rotors[0] = '7';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Encrypt(encryption_rotors, 1, encrypted_message, encrypted_message);
    // printf("#4 -%s-\n", encrypted_message);

    // char testmsg[] = "IRONMAN VS BATMAN";
    // char testencrypted_message[80];
    // which_rotors[0] = '1';
    // which_rotors[1] = '2';
    // which_rotors[2] = '8';
    // which_rotors[3] = '7';
    // which_rotors[4] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Encrypt(encryption_rotors, 4, testmsg, testencrypted_message);
    // printf("ex -%s-\n", testencrypted_message);

    // which_rotors[0] = '7';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Decrypt(encryption_rotors, 1, encrypted_message, decrypted_message);
    // printf("#4 -%s-\n", decrypted_message);

    // which_rotors[0] = '8';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Decrypt(encryption_rotors, 1, decrypted_message, decrypted_message);
    // printf("#3 -%s-\n", decrypted_message);

    // which_rotors[0] = '2';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Decrypt(encryption_rotors, 1, decrypted_message, decrypted_message);
    // printf("#2 -%s-\n", decrypted_message);

    // which_rotors[0] = '1';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Decrypt(encryption_rotors, 1, decrypted_message, decrypted_message);
    // printf("#1 -%s-\n", decrypted_message);

    // which_rotors[0] = '1';
    // which_rotors[1] = '2';
    // which_rotors[2] = '8';
    // which_rotors[3] = '7';
    // which_rotors[4] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Decrypt(encryption_rotors, 4, testencrypted_message, decrypted_message);
    // printf("ex -%s-\n", decrypted_message);

    // printf("Enter the message to be encrypted or decrypted: ");
    // Get_Message(message);
    // printf("message |%s|\n", message);

    // char set_rotors_test[] = {'3', '1', '\0'};
    // char set_rotors_test[] = {'1', '2', '3', '4', '\0'};
    // char set_rotors_test[] = {'1', '5', '8', '4', '\0'};
    // // char set_rotors_test[] = {'1', '\0'};

    // Set_Up_Rotors(encryption_rotors, set_rotors_test);
    // for (int i = 0; set_rotors_test[i]; i++)
    // {
    //     if (!compareRotors(ROT[set_rotors_test[i] - '0'], encryption_rotors[i]))
    //     {

    //         printf("wrong: %d\n", i);
    //     }
    // }

    // char rotate[] = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    // rotations = 29;
    // Rotate_Rotor(rotations, rotate);
    // if (!compareRotors("SQOBDFHJLCPRTXVZNYEIWGAKMU", rotate))
    // {
    //     printf("%s\n", rotate);
    // }

    // Rotate_Rotor(-1 * rotations, rotate);
    // if (!compareRotors("BDFHJLCPRTXVZNYEIWGAKMUSQO", rotate))
    // {
    //     printf("%s\n", rotate);
    // }

    // Apply_Rotation(rotations, encryption_rotors);
    // Apply_Rotation(-1 * rotations, encryption_rotors);
    // for (int i = 0; set_rotors_test[i]; i++)
    // {
    //     if (!compareRotors(ROT[set_rotors_test[i] - '0'], encryption_rotors[i]))
    //     {

    //         printf("wrong: %d\n", i);
    //     }
    // }

    // char msg[] = "JAVA";
    // char rotor[] = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    // char encrypt[1024] = "jkfljkefljkel";
    // Encrypt_One_Rotor(rotor, msg, encrypt);

    // printf(",%s,\n", encrypt);

    // char decrypt[1024] = "ABCDE";
    // Decrypt_One_Rotor(rotor, encrypt, decrypt);
    // printf(",%s,\n", decrypt);

    // Encrypt(encryption_rotors, 1, msg, encrypt);
    // printf(",%s,", encrypt);

    // Decrypt(encryption_rotors, 1, encrypt, decrypt);
    // printf(",%s,\n", decrypt);

    // printf("Enter the message to be encrypted or decrypted: ");
    // Get_Message(message);
    // printf("message |%s|\n", message);

    // printf("Which rotors will be used to encrypt the message: ");
    // num_active_rotors = Get_Which_Rotors(which_rotors);
    // printf("%s \t %d\n", which_rotors, num_active_rotors);

    // printf("Enter the number of rotations to apply to the encryption rotors: ");
    // rotations = Get_Rotations();
    // printf("%d\n", rotations);

    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(rotations, encryption_rotors);

    // Encrypt(encryption_rotors, num_active_rotors, message, encrypted_message);
    // Decrypt(encryption_rotors, num_active_rotors, encrypted_message, decrypted_message);

    // printf("The encrypted message is: %s\n", encrypted_message);

    // printf("The decrypted message is: %s", decrypted_message);

    // which_rotors[0] = '1';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // char msg[] = "IRONMAN VS BATMAN";
    // printf("og -%s-\n", msg);
    // Encrypt(encryption_rotors, 1, msg, encrypted_message);
    // printf("#1 -%s-\n", encrypted_message);

    // which_rotors[0] = '2';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Encrypt(encryption_rotors, 1, encrypted_message, encrypted_message);
    // printf("#2 -%s-\n", encrypted_message);

    // which_rotors[0] = '8';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Encrypt(encryption_rotors, 1, encrypted_message, encrypted_message);
    // printf("#3 -%s-\n", encrypted_message);

    // which_rotors[0] = '7';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Encrypt(encryption_rotors, 1, encrypted_message, encrypted_message);
    // printf("#4 -%s-\n", encrypted_message);

    // char testmsg[] = "IRONMAN VS BATMAN";
    // char testencrypted_message[80];
    // which_rotors[0] = '1';
    // which_rotors[1] = '2';
    // which_rotors[2] = '8';
    // which_rotors[3] = '7';
    // which_rotors[4] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Encrypt(encryption_rotors, 4, testmsg, testencrypted_message);
    // printf("ex -%s-\n", testencrypted_message);

    // which_rotors[0] = '7';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Decrypt(encryption_rotors, 1, encrypted_message, decrypted_message);
    // printf("#4 -%s-\n", decrypted_message);

    // which_rotors[0] = '8';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Decrypt(encryption_rotors, 1, decrypted_message, decrypted_message);
    // printf("#3 -%s-\n", decrypted_message);

    // which_rotors[0] = '2';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Decrypt(encryption_rotors, 1, decrypted_message, decrypted_message);
    // printf("#2 -%s-\n", decrypted_message);

    // which_rotors[0] = '1';
    // which_rotors[1] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Decrypt(encryption_rotors, 1, decrypted_message, decrypted_message);
    // printf("#1 -%s-\n", decrypted_message);

    // which_rotors[0] = '1';
    // which_rotors[1] = '2';
    // which_rotors[2] = '8';
    // which_rotors[3] = '7';
    // which_rotors[4] = '\0';
    // Set_Up_Rotors(encryption_rotors, which_rotors);
    // Apply_Rotation(3, encryption_rotors);
    // Decrypt(encryption_rotors, 4, testencrypted_message, decrypted_message);
    // printf("ex -%s-\n", decrypted_message);

    // printf("Enter the message to be encrypted or decrypted: ");
    // Get_Message(message);
    // printf("message |%s|\n", message);

        return 0;
}
