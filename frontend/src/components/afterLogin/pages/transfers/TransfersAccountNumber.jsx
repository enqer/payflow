import React, {useEffect, useState} from 'react';
import styles from "./TransfersAccountNumber.module.css";
import TextInput from "../../common/inputs/textInput"
import axios from "axios";
import {config, user} from "../../../../config/authConfig";
import {isAccountNumberValid} from "../../../utils/validation";
import SelectAccountNumber from "./SelectAccountNumber";
import toast from "react-hot-toast";


function TransfersAccountNumber() {
    const [recipientAccountNumber, setRecipientAccountNumber] = useState('');
    const [title, setTitle] = useState('');
    const [amount, setAmount] = useState('');
    const [data, setData] = useState([])
    const [fromAccount, setFromAccount] = useState('')


    const getAccountNumbers = () => {
        axios
            .get(`http://localhost:8080/api/v1/users/${user.userId}/numbers`,
                config
            )
            .then((response) => {
                setData(response.data)
            })
            .catch((error) => {
                console.log(error)
            })
    }

    useEffect(() => {


        getAccountNumbers()
    }, []);

    const validate = () => {
        if (fromAccount !== '')
        return isAccountNumberValid(recipientAccountNumber)
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        if (!validate())
            return

        axios
            .post(`http://localhost:8080/api/v1/transfer`,
                {
                    amount: amount,
                    description: title,
                    senderAccountNumber: fromAccount,
                    receiverAccountNumber: recipientAccountNumber
                },
                config
            )
            .then((response) => {
                toast('Wysłano')
            })
            .catch((error) => {
                toast('Operacja nie powiodła się')
            })
    };

    return (
        <div className={styles.transferAccountNumberContainer}>
            <h3>Na numer konta</h3>
            <form className={styles.formStyle} onSubmit={handleSubmit}>
                <div className={styles.selectAccount}>
                    <p>Z konta:</p>
                    <SelectAccountNumber
                        data={data}
                        selectedAccounts={setFromAccount}
                    />
                </div>
                <TextInput
                    state={setRecipientAccountNumber}
                    name={"Numer rachunku odbiorcy"}
                    type={"text"}
                />
                <TextInput
                    state={setTitle}
                    name={"Tytuł"}
                    type={"text"}
                />
                <TextInput
                    state={setAmount}
                    name={"Kwota"}
                    type={"number"}
                />
                <div className={styles.submitWrapper}>
                    <input className={styles.submit} type="submit" value="Wyślij"/>
                </div>
            </form>
        </div>
    );
}

export default TransfersAccountNumber;
