import React from 'react';
import './card.css';

const Card = ({question}) => {
    return (
        <div className="card">
            <p>{question}</p>
        </div>
    );
}


export default Card;