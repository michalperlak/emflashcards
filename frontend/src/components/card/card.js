import React, {useState} from 'react';
import './card.css';

const Card = ({question, answer, notes}) => {
    const [showAnswer, setShowAnswer] = useState(false);

    return (
        <div className="card">
            <p>{question}</p>
            {!showAnswer && (<button onClick={() => setShowAnswer(true)}>Show answer</button>)}
            {showAnswer && (<p>{answer}</p>)}
        </div>
    );
}


export default Card;