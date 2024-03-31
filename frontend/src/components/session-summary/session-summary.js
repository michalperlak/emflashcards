import React from 'react';
import './session-summary.css';

const RATE_AGAIN = 1;
const RATE_HARD = 2;
const RATE_GOOD = 3;
const RATE_EASY = 4;

const countByRating = (session, rating) => {
    return Object
        .entries(session.rates)
        .filter(([,value]) => value === rating)
        .length;
}

const SessionSummary = ({session}) => {
    return (
        <div className="box summary">
            <div className="columns mt-1">
                <h1 className="title is-4">Session summary</h1>
            </div>
             <div className="columns mt-10">
                 <span className="has-text-weight-bold">Total cards: </span>
                 <span className="is-italic">{session.cards.length}</span>
             </div>
            <div className="columns">
                <span className="has-text-weight-bold">Started: </span>
                <span className="is-italic">{session.started.toLocaleString()}</span>
            </div>
            <div className="columns">
                <span className="has-text-weight-bold">Finished: </span>
                <span className="is-italic">{session.finished.toLocaleString()}</span>
            </div>
            <div className="columns mt-10"></div>

            <div className="columns">
                <span className="has-text-weight-bold">Easy: </span>
                <span className="is-italic">{countByRating(session, RATE_EASY)}</span>
            </div>
            <div className="columns">
                <span className="has-text-weight-bold">Good: </span>
                <span className="is-italic">{countByRating(session, RATE_GOOD)}</span>
            </div>
            <div className="columns">
                <span className="has-text-weight-bold">Hard: </span>
                <span className="is-italic">{countByRating(session, RATE_HARD)}</span>
            </div>
            <div className="columns">
                <span className="has-text-weight-bold">Again: </span>
                <span className="is-italic">{countByRating(session, RATE_AGAIN)}</span>
            </div>
        </div>
    );
}


export default SessionSummary;