import { useState } from "react";

import UploadSection from "./components/UploadSection";
import SummaryCard from "./components/SummaryCard";
import ChatSection from "./components/ChatSection";
import TimestampSearch from "./components/TimestampSearch";

function App() {

  const [uploadedFile, setUploadedFile] =
    useState(null);

  return (

    <div className="min-h-screen bg-gradient-to-br from-gray-100 to-gray-300">
      {/* <nav className="bg-white shadow-md p-4 rounded-xl mb-8 flex justify-between items-center">

        <h1 className="text-2xl font-bold text-blue-700">
          PanScience AI Platform
        </h1>

        <span className="text-gray-500">
          Spring Boot + React + AI
        </span>

      </nav> */}
      <div className="max-w-5xl mx-auto py-10 px-6">

        <div className="text-center mb-10">

          <h1 className="text-5xl font-extrabold text-gray-800">

            AI Multimedia Q&A Platform

          </h1>

          <p className="text-gray-600 mt-4 text-lg">

            Upload PDFs, audio, and videos to interact
            with AI-powered summaries, Q&A, and
            timestamp search.

          </p>

        </div>

        <div className="space-y-6">

          <UploadSection
            setUploadedFile={setUploadedFile}
          />

          <SummaryCard
            uploadedFile={uploadedFile}
          />

          <ChatSection
            uploadedFile={uploadedFile}
          />

          <TimestampSearch
            uploadedFile={uploadedFile}
          />

        </div>

      </div>

    </div>
  );
}

export default App;