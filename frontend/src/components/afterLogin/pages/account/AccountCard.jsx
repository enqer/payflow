import styles from './Account.module.css'
import CreditCard from "../cards/CreditCard";

const AccountCard= (props) => {
    console.log(props.cardNumber)
    return(
        <div style={{padding: 10}}>
          <CreditCard currency={props.currency}
                      balance={props.balance}
                      cardNumber={props.cardNumber}
                      owner={props.owner}
                      expiration={props.validDate}
                      cvv={props.cvv}
                      cardStyle={4}
                      details={false}
                      isClicked={true}
          />
        </div>
    );
}
export default AccountCard;
