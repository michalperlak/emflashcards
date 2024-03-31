import React, {useContext, useState} from 'react';
import './card-answer.css';
import {rateCard} from "../../application/cards";
import {AppContext} from "../../context/app-context";

const RATE_AGAIN = 1;
const RATE_HARD = 2;
const RATE_GOOD = 3;
const RATE_EASY = 4;


const CardAnswer = ({answer, cardId, session}) => {
    const {user, updateState} = useContext(AppContext);
    const [isDone, setIsDone] = useState(session.done.includes(cardId));
    const [rate, setRate] = useState(session.rates[cardId] || -1);

    const handleRate = (clickEvent, newRate) => {
        clickEvent.stopPropagation();
        clickEvent.nativeEvent.stopImmediatePropagation();
        setRate(newRate);
        setIsDone(true);

        rateCard(user, cardId, newRate)
            .then(() => {
                session.done.push(cardId);
                session.rates[cardId] = newRate;
                updateState({activeSession: session});
                alert("Your rate: " + newRate);
            })
    };

    return (
        <div className="card-answer fixed-grid has-4-cols">
            <div className="grid mt-20 has-text-centered ml-10 mr-10">
                <div className="cell is-col-span-4 mb-20">
                    <p>{answer}</p>
                </div>
                <div className="cell">
                    <button
                        className={`button w-95 ${isDone && rate !== RATE_AGAIN ? "is-light" : "is-danger"} ${isDone ? "" : "is-light"} ${isDone ? "click-disabled" : ""}`}
                        onClick={(e) => handleRate(e, RATE_AGAIN)}>Again
                    </button>
                </div>
                <div className="cell">
                    <button
                        className={`button w-95 ${isDone && rate !== RATE_HARD ? "is-light" : "is-warning"} ${isDone ? "" : "is-light"} ${isDone ? "click-disabled" : ""}`}
                        onClick={(e) => handleRate(e, RATE_HARD)}>Hard
                    </button>
                </div>
                <div className="cell">
                    <button
                        className={`button w-95 ${isDone && rate !== RATE_GOOD ? "is-light" : "is-info"} ${isDone ? "" : "is-light"} ${isDone ? "click-disabled" : ""}`}
                        onClick={(e) => handleRate(e, RATE_GOOD)}>Good
                    </button>
                </div>
                <div className="cell">
                    <button
                        className={`button w-95 ${isDone && rate !== RATE_EASY ? "is-light" : "is-success"} ${isDone ? "" : "is-light"} ${isDone ? "click-disabled" : ""}`}
                        onClick={(e) => handleRate(e, RATE_EASY)}>Easy
                    </button>
                </div>
            </div>
        </div>
    );
}


export default CardAnswer;